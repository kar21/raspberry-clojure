(ns pi.hardware)
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

(comment
  (gpio/close led-handle)
  ;
  )

(def buffer
  (gpio/buffer led-handle))

(def watcher
  (gpio/watcher device
                {21 {:gpio/tag       :button1
                     :gpio/direction :input
                     :gpio/edge-detection :rising}}))

(comment
  (gpio/close watcher)

  ;
  )

(defn on-click [f]
  (future
    (while true
      (when-some [event (gpio/event watcher 200)]
        (f event)))))


(defn log-event [event]
  (println (str "Button pressed: " 
                (:gpio/nano-timestamp event)
                " "
                (:gpio/tag event))))

(comment
  (def t (on-click log-event))
  (future-cancel t) 
    
) 