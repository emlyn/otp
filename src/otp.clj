(ns otp
 (:require [clj-otp.core :refer [totp]]
           [clj-otp.base32 :refer [decode-data]]))

(defn otp
  [s]
  (totp (decode-data s)))

(defn read-secret
  []
  (if-let [console (System/console)]
    (if-let [secret (.readPassword console "Enter secret: " (object-array 0))]
      (String. secret))
    (read-line)))

(defn check-secret
  [secret]
  (if (every? (set "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ") secret)
    secret
    (binding [*out* *err*]
      (println "Invalid secret, expecting base-32 string (only 0-9, A-Z)"))))

(defn -main [& [s]]
  (if-let [secret (check-secret (or s (read-secret)))]
    (println (otp secret))
    (System/exit 1)))
