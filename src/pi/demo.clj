(require '[helins.linux.gpio :as gpio])


;; Alternating between 2 leds every time a button is released.
;;
;; After opening a GPIO device, we need a handle for driving the leds and a watcher
;; for monitoring the button. A buffer is used in conjunction with the handle in
;; order to describe the state of the leds.

(def device         
  (gpio/device "/dev/gpiochip0"))

(def  led-handle     (gpio/handle device
                                  {17 {:gpio/state false
                                       :gpio/tag   :led-1}
                                   18 {:gpio/state true
                                       :gpio/tag   :led-2}}
                                  {:gpio/direction :output}) )

(def buffer 
   (gpio/buffer led-handle)
  )

 (gpio/write led-handle
             (gpio/set-line+ buffer
                                  ;{(first  leds) true
                                  ; (second leds) true}
                             {:led-1 true}))

(def state (atom true))


(defn toggle-led []
  (swap! state not)
  (println "led-state: " @state)
   (gpio/write led-handle
               (gpio/set-line+ buffer
                                  ;{(first  leds) true
                                  ; (second leds) true}
                               {:led-1 @state}))
  )

(toggle-led)

(def watcher 
  (gpio/watcher device
             {21 {:gpio/tag       :button1
                  :gpio/direction :input
                  :gpio/edge-detection :falling
                  }
      })
)


 (while true
   (println (if-some [event (gpio/event watcher
                                        200)]
              (do (str "Button for line has been pressed"
                       (:gpio/nano-timestamp event)
                       (:gpio/tag event))
                   (toggle-led)
                  )
              
              "Timeout !")))

    (while true
         (println (if-some [event (gpio/event watcher
                                              200)]
                    (str "Button for line has been pressed"
                            (:gpio/nano-timestamp event)
                            (:gpio/tag event))
                    "Timeout !")))


(with-open [
           
            ;button-watcher (gpio/watcher device
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
                                       :gpio/tag   :2}
                                   
                                   
                                   
                                   }
                                  {:gpio/direction :output}))

(def buffer-bar
  (gpio/buffer bar-handle))


 (gpio/write bar-handle
             (gpio/set-line+ buffer-bar 
                             {1 false
                              :2 false
                              }))
