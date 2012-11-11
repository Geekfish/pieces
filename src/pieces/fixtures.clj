(ns pieces.fixtures
  (:use pieces.models
        korma.db
        korma.core)
  (:require [clj-yaml.core :as yaml]))

(defn load-fixtures
  "Loads YAML fixtures from 'resources/fixtures.yml' into databse "
  []
  (let [{u :users p :posts} (yaml/parse-string (slurp "./resources/fixtures.yml"))]
    (insert users (values u))
    (insert posts (values p))))