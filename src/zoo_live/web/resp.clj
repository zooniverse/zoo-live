(ns zoo-live.web.resp)

(def stream-mime "application/vnd.zooevents.stream.v1+json")
(def app-mime "application/vnd.zooevents.v1+json")
 
(defn resp-ok
  [body & [content-type]]
  {:status 200
   :headers {"Content-Type" (or content-type app-mime)}
   :body body})

(defn resp-bad-request
  []
  {:status 401
   :headers {"Content-Type" app-mime}
   :body {"status" "Bad Request"}})
 
