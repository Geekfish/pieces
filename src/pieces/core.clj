(ns pieces.core
  (:use ring.adapter.jetty
        ring.middleware.resource
        ring.middleware.reload
        ring.middleware.file
        ring.middleware.params
        ring.middleware.session
        ring.middleware.session.cookie
        net.cgrand.moustache
        pieces.controller))


;; Routes definition
(def routes
  (app
    (wrap-file "resources/public")
    (wrap-params)
    (wrap-session {:cookie-name "pieces-session" :store (cookie-store)})
    ["login"] (delegate login)
    ["logout"] (delegate logout)
    ["admin"] (delegate admin)
    [""] (delegate index)
    [id] (delegate post id)))


;;; start function for starting jetty
(defn start [port]
  (run-jetty #'routes {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt (or (System/getenv "PORT") "9000"))]
    (start port)))