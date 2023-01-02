(require '[helins.linux.gpio :as gpio])


;; Alternating between 2 leds every time a button is released.
;;
;; After opening a GPIO device, we need a handle for driving the leds and a watcher
;; for monitoring the button. A buffer is used in conjunction with the handle in
;; order to describe the state of the leds.


(with-open [device         (gpio/device "/dev/gpiochip0")
            led-handle     (gpio/handle device
                                        {17 {:gpio/state false
                                             :gpio/tag   :led-1}
                                         18 {:gpio/state true
                                             :gpio/tag   :led-2}}
                                        {:gpio/direction :output})
            button-watcher (gpio/watcher device
                                         {22 {:gpio/direction :input}})]
  (let [buffer (gpio/buffer led-handle)]
    (loop [leds (cycle [:led-1
                        :led-2])]
      (gpio/write led-handle
                  (gpio/set-line+ buffer
                                  {(first  leds) true
                                   (second leds) false}))
      (gpio/event button-watcher)
      (recur (rest leds)))))