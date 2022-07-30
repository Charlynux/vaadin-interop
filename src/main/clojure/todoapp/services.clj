(ns todoapp.services
    (:require [clojure.string :as str]))

(defn my-get-label [label]
  (str/capitalize label))

(defn create-label-service
  []
  (reify
    com.example.application.views.main.LabelService
    (getLabel [_ label] (my-get-label label))))