(ns pieces.templates
  (:use net.cgrand.enlive-html))

(deftemplate home-page  "home.html" [posts]
  [:title] (content "Bits and pieces")
  [:div.post] (clone-for [post posts]
                [:a.title] (do->
                             (set-attr :href (str "/" (:id post)))
                             (content (:title post)))
                [:div.content (html-content (:content post))]))

(deftemplate post-page "post.html" [post]
  [:title] (content (str "Pieces - " (:title post)))
  [:span.title] (content (:title post))
  [:div.content] (html-content (:content post)))

(deftemplate login-page "login.html" [& msg]
  [:div#error] (if (nil? msg)
                 (set-attr :style "display:none")
                 (do->
                   (remove-attr :style)
                   (content msg))))

(deftemplate admin-page "admin.html" [])