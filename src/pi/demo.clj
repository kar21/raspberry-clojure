(require '[helins.linux.gpio :as gpio])

; https://github.com/helins/linux.gpio.clj

;; Alternating between 2 leds every time a button is released.
;;
;; After opening a GPIO device, we need a handle for driving the leds and a watcher
;; for monitoring the button. A buffer is used in conjunction with the handle in
;; order to describe the state of the leds.

(def device
  (gpio/device "/dev/gpiochip0"))

(def  led-handle     (gpio/handle device
                                  {16 {:gpio/state false
                                       :gpio/tag   :led-1}
                                   24 {:gpio/state true
                                       :gpio/tag   :led-2}
                                   23 {:gpio/state true
                                       :gpio/tag   :led-3}
                                   18 {:gpio/state true
                                       :gpio/tag   :led-4}
                                   12 {:gpio/state true
                                       :gpio/tag   :led-5}
                                   25 {:gpio/state true
                                       :gpio/tag   :led-6}
                                   22 {:gpio/state true
                                       :gpio/tag   :led-7}
                                   26 {:gpio/state true
                                       :gpio/tag   :led-8}
                                   19 {:gpio/state true
                                       :gpio/tag   :led-9}
                                   13 {:gpio/state true
                                       :gpio/tag   :led-10}}
                                  {:gpio/direction :output}))

(gpio/close led-handle)








(def buffer
  (gpio/buffer led-handle))

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
               :led-10 true} ])



(get patterns 2)
(defn write-patterns [i]
  (gpio/write led-handle
              (gpio/set-line+ buffer
                              (get patterns i))))

(write-patterns 0)
(write-patterns 1)
(write-patterns 2)
(write-patterns 3)
(write-patterns 4)


(defn next-index [i]
  (let [n (inc i)]
    (if (= n (count patterns))
      0
      n)))



(next-index 0)
(next-index 1)
(next-index 2)
(next-index 3)
(next-index 4)

(def state (atom 0))
(defn step []
  (swap! state next-index)
  (write-patterns @state))
(step)

(future
  (loop []
    (step)
    (Thread/sleep 500)
    (recur)))
  
  



()








(def state (atom true))






(defn toggle-led []
  (swap! state not)
  (println "led-state: " @state)
  (gpio/write led-handle
              (gpio/set-line+ buffer
                                  ;{(first  leds) true
                                  ; (second leds) true}
                              {:led-1 @state})))

(toggle-led)

(def watcher
  (gpio/watcher device
                {21 {:gpio/tag       :button1
                     :gpio/direction :input
                     :gpio/edge-detection :falling}}))


(while true
  (println (if-some [event (gpio/event watcher
                                       200)]
             (do (str "Button for line has been pressed"
                      (:gpio/nano-timestamp event)
                      (:gpio/tag event))
                 (toggle-led))

             "Timeout !")))

(while true
  (println (if-some [event (gpio/event watcher
                                       200)]
             (str "Button for line has been pressed"
                  (:gpio/nano-timestamp event)
                  (:gpio/tag event))
             "Timeout !")))


(with-open [;button-watcher (gpio/watcher device
            ;                             {22 {:gpio/direction :input}})
            ]
  (let [buffer]
   ; (loop [leds (cycle [:led-1
    ;                    :led-2])]

     ; (gpio/event button-watcher)
    (println "setting leds")
   ;   (recur (rest leds))
    ))


(gpio/close bar-handle)

(def  bar-handle     (gpio/handle device
                                  {26 {:gpio/state false
                                       :gpio/tag   1}
                                   13 {:gpio/state true
                                       :gpio/tag   :2}}
                                  {:gpio/direction :output}))

(def buffer-bar
  (gpio/buffer bar-handle))


(gpio/write bar-handle
            (gpio/set-line+ buffer-bar
                            {1 true
                             :2 true}))

