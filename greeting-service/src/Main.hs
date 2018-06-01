{-# LANGUAGE OverloadedStrings #-}

module Main where

import Network (listenOn, PortID(PortNumber))
import Network.Socket (accept, withSocketsDo, close, Socket)
import Network.Socket.ByteString (recv, sendAll)
import Control.Concurrent (forkIO)
import Data.List.Split
import Data.Map.Lazy (fromList)
import qualified Data.ByteString.Lazy.Char8 as LazyByte (toChunks)
import qualified Data.ByteString as  ByteString (concat, pack)
import qualified Data.ByteString.Char8 as Char8 (unpack, pack)
import qualified Data.Text.Lazy as LazyText (Text, length, pack, concat)
import Data.Text.Lazy.Encoding (encodeUtf8)
import qualified Greeter as Greeter (greet)
import Data.List (isInfixOf)
import qualified Data.Map as Map (size, null)
import qualified Data.Map.Internal as InternalMap

main = withSocketsDo $ do
  sock <- listenOn (PortNumber 1919)
  loop sock

handleNotFound :: Request -> Response
handleNotFound request =
    let notFoundBase = LazyText.pack "Route not found."
        newLine      = LazyText.pack "\r\n"

        method2      = method request
        yourMethodBase = LazyText.pack "Your request method: "
        yourMethod = LazyText.concat [yourMethodBase, method2, newLine]

        path2 = path request
        yourPathBase = LazyText.pack "Your request path: "
        yourPath = LazyText.concat [yourPathBase, path2, newLine]

--         params = LazyText.pack . show . queryParams $ request
--         yourParamsBase = LazyText.pack "Your query params: "
--         yourParams = LazyText.concat [yourParamsBase, params, newLine]

        body = LazyText.concat [notFoundBase, newLine, yourMethod, yourPath]

    in notFoundResp "Not Found" body

handleGreeting :: Request -> Response
handleGreeting request =
    let firstName = "David"
        lastName = "Bell"
        body = Greeter.greet firstName lastName

    in okResp body

handleResponse :: Request -> Response
handleResponse request
    | path2 == "/greeting" = handleGreeting request
    | otherwise = handleNotFound request
    where path2 = path request

-- getQueryParams :: [Char] -> InternalMap
-- getQueryParams fullPath
--     | isInfixOf "?" fullPath = fromList([("query","params"), ("firstname","IT"), ("lastname","Sammy")])
--     | otherwise = Map.null

loop :: Socket-> IO()
loop sock = do
    (conn, _) <- accept sock
    forkIO $ respond conn
    loop sock
  where
    respond conn = do
      msg <- recv conn 1024
      handleRequest conn $ getRequest msg
      close conn
    handleRequest conn request = sendResponse conn $ handleResponse request
    getRequest msg =
       let  charMsg = Char8.unpack msg
            requestSplit = splitOn "\r\n" charMsg

            [method, fullPath, httpVersion] = splitOn " " $ requestSplit!!0
            path = (!!0) . splitOn "?" $ fullPath

            methodText = LazyText.pack method
            pathText = LazyText.pack path

--             params = getQueryParams fullPath

            requestTrim = init . init . tail $ requestSplit
            headers = map (splitOn ": ") requestTrim

       in HttpRequest{ method = methodText, path = pathText, httpVersion = httpVersion, headers = headers}
    sendResponse conn resp =
       sendAll conn (ByteString.concat . LazyByte.toChunks $ toByteString resp)


data Request = HttpRequest {
    method :: LazyText.Text,
    path :: LazyText.Text,
    httpVersion :: [Char],
--     queryParams :: [Char],
    headers :: [[[Char]]]
} deriving( Show, Eq )

data Response = TextResponse {
  version :: LazyText.Text,
  status :: Int,
  reason :: LazyText.Text,
  text :: LazyText.Text
} deriving (Show, Eq)

okResp text = TextResponse {
  version = "1.1", status = 200, reason = "Ok", text = text
}

notFoundResp reason text = TextResponse {
  version = "1.1", status = 404, reason = reason, text = text
}

toByteString (TextResponse {
  version = ver,
  status = stat,
  reason = reason,
  text = text
}) = encodeUtf8 . LazyText.concat $ [
    "HTTP/", ver, " ", LazyText.pack . show $ stat, reason, "\r\n",
    "Content-Length: ", LazyText.pack . show $ LazyText.length text, "\r\n",
    "\r\n",
    text]