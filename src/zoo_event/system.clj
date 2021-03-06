(ns zoo-event.system
  (:require [com.stuartsierra.component :as component]
            [zoo-event.component.database :refer [new-database]]
            [zoo-event.component.kafka :refer [new-kafka]]
            [zoo-event.component.app :refer [new-app]]
            [zoo-event.web.routes :as r]))

(defn system
  "Returns a new instance of the whole application"
  [{:keys [events port jdbc kafka-config project-uri types]
    :or {port 8080 events ["classifications"]}}]
  (component/system-map
    :db (new-database events jdbc)
    :kafka (new-kafka kafka-config)
    :app (component/using 
           (new-app port r/handler)
           [:db :kafka])))

