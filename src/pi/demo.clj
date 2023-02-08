(ns pi.demo
  (:require
   [pi.hardware :refer [buffer led-handle watcher on-click]]
   [helins.linux.gpio :as gpio]))


(comment 
  (gpio/write led-handle
              (gpio/set-line+ buffer
                                  ;{(first  leds) true
                                  ; (second leds) true}
                              {:led-1 false
                               :led-2 false
                               :led-3 false
                               :led-4 false
                               :led-5 false
                               :led-6 false
                               :led-7 false
                               :led-8 false
                               :led-9 false
                               :led-10 false}))
  )



(def patterns [{:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 true
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 true
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 true
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 true
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 true
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 true
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 true
                :led-8 false
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 true
                :led-9 false
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 true
                :led-10 false}
               {:led-1 false
                :led-2 false
                :led-3 false
                :led-4 false
                :led-5 false
                :led-6 false
                :led-7 false
                :led-8 false
                :led-9 false
                :led-10 true}])


; (get patterns 2)


(defn write-patterns [i]
  (gpio/write led-handle
              (gpio/set-line+ buffer
                              (get patterns i))))

(comment
  (write-patterns 0)
(write-patterns 1)
(write-patterns 2)
(write-patterns 3)
(write-patterns 4)
  
  )




(defn next-index [i]
  (let [n (inc i)]
    (if (= n (count patterns))
      0
      n)))


(comment
  (next-index 0)
(next-index 1)
(next-index 2)
(next-index 3)
(next-index 4)
  )


(def state (atom 0))

(defn step []
  (swap! state next-index)
  (write-patterns @state))

(comment
  (step)
  )


(defn button-clip [event]
  (step))




(defn start-app [& args]
  (on-click button-clip)
  (loop []
    (Thread/sleep 500)
    (recur))
  )


(defn animate []
  (future
    (loop []
      (step)
      (Thread/sleep 500)
      (recur)))
  )

; [++++++++++]
; [          ]
; [   +      ]


