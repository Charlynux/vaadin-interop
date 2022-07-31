(ns todoapp.services
    (:require [clojure.string :as str]
              [clojure.core.match :refer [match]]))

(defn my-get-label [label]
  (str/capitalize label))

(defn create-label-service
  []
  (reify
    com.example.application.views.main.LabelService
    (getLabel [_ label] (my-get-label label))))

(defn fizzbuzz [n]
    (match [(mod n 3) (mod n 5)]
          [0 0] "FizzBuzz"
          [0 _] "Fizz"
          [_ 0] "Buzz"
          :else n))