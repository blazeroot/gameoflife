(ns gameoflife.core
  (:require [gameoflife.functions :refer :all]
            [gameoflife.ui :as ui]
            [clojure.core.matrix :as m]))

(def world-state (atom (generate-world 30)))

(defn run
  "Runs game of life."
  []
  (while true
    (do
      (ui/clear)
      (ui/print-world @world-state)
      (Thread/sleep 333)
      (swap! world-state step))))
