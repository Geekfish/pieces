(comment "Entity definitions for Pieces using Korma.
          To get the list of users from the database run the following code in REPL:
          (use 'korma.db 'korma.core 'pieces.models)
          (select authors)
          ")

(ns pieces.models
  (:use korma.db
        korma.core))

;;; Defines the database for lobos migrations
(defdb pieces-db
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname "pieces-db"
   :user "pieces"
   :password "piecespass"})

(defentity users)
(defentity posts)

(defn user-by-username [username]
  (first
    (select users (where {:username username}))))