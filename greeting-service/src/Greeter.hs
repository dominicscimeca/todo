{-# LANGUAGE OverloadedStrings #-}

module Greeter where
import Prelude hiding (concat)
import Data.Text.Lazy (Text, length, pack, concat)

greet :: Text -> Text -> Text
greet firstName lastName =
    concat $ ["Welcome ", lastName, ", ", firstName]