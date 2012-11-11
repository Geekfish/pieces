(ns pieces.controller
  (:use pieces.templates
        pieces.models
        pieces.authentication
        pieces.debug
        ring.util.response
        korma.core))

(defn index
  "Index page handler"
  [req]
  (->> (select posts) home-page response))

(defn post
  "Post details page handler"
  [req id]
  (let [postId (Integer/parseInt id)]
    (->> (first (select posts (where {:id postId})))
      post-page response)))

(defn password-valid? [username password]
  (= (:password (user-by-username username))
     (md5-encrypt password)))

(defn login
  "Login Handler"
  [req]
  (let [params (:params req)]
    (if (empty? params)
      (response (login-page))
      (let [username (get params "username")
            password (get params "password")]
        (if (password-valid? username password)
          (let [user (user-by-username username)]
            (assoc (redirect "/admin") :session {:user-id (:id user)}))
          (response (login-page "Invalid username or password")))))))

(defn logout
  "Logout handler"
  [req]
  (assoc (redirect "/") :session nil))

(defn admin
  "Admin handler"
  [req]
  (let [user-id (:user-id (:session req))
        params (:params req)]
    (if (nil? user-id)
      (redirect "/login")
      (do
        (if-not (empty? params)
          (let [id (inc (count (select posts)))
                user-id (:id (first (select users (fields :id) (where {:id user-id}))))]
            (insert posts (values (assoc params
                                    :id id
                                    :user user-id)))))
        (response (admin-page))))))