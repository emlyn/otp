# OTP

Simple command line one-time password generator for MFA in clojure.

Mainly a proof of concept: you shouldn't really be storing the secret key anywhere once
entered into the authenticator app.

Should generate the same tokens as Google Authenticator, Lastpass Authenticator etc.
Does not store the secret (to avoid dealing with securing it), so you must provide it each time.
If you use it with a real secret, make sure that is stored somewhere safe, and preferably
not with its corresponding password.

## Usage

Make sure you have Clojure 1.9 or higher installed (on Mac `brew install clojure`).

You can provide the secret on the command line:
```bash
$ ./otp SECRET-HERE
123456
```

But beware that this will make the secret visible to other programs and store it in your command line history, so is not safe for real world use.

You can therefore provide the secret on the standard input:
```bash
$ cat my-secret-file | ./otp
123456
```

Or enter/paste it when prompted:
```bash
$ ./otp
Enter secret: *********************************
123456
```
