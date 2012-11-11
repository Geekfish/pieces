(defproject pieces "0.0.1-SNAPSHOT"
  :description "Pieces: consolidated microblogging"
  :url "http://pieces.eleni.co/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main pieces.core
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.6"]
                 [net.cgrand/moustache "1.1.0"]
                 [lobos "1.0.0-SNAPSHOT"]
                 [korma "0.3.0-beta7"]
                 [enlive "1.0.0"]
                 [postgresql "9.1-901.jdbc4"]
                 [clj-yaml "0.3.1"]
                 [digest "1.3.0"]])
