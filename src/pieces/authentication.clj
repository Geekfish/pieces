(ns pieces.authentication
  (:require digest))

(defn md5-encrypt
  "Get enctrypted string"
  [raw-string]
  (digest/md5 raw-string))