# SimplePinLock

Simple PIN lock implementation for Android.

<img src="https://raw.githubusercontent.com/aquarla/SimplePinLock/master/images/screenshot.png" width="200">

## How to install

Under Construction...

## How to use

### Registering new PIN

Create new activity overriding ``SimpleRegisterPinLockActivity``.

Function ``onPinRegistered()`` is called if PIN is correctly entered. You must implement a functionality of saving the PIN.

~~~
class MyRegisterPinLockActivity : SimpleRegisterPinLockActivity() {
    override fun onPinRegistered(pin: String) {
        // Save new PIN into SharedPreferenece or other...
        
    }
}
~~~

### Confirming PIN

Create new activity overriding ``SimpleConfirmPinLockActivity``.

Function ``isPinCorrect()`` is called when user enters PIN. You must implement a functionality of checking whether the PIN is correct or not.

~~~
class MyConfirmPinLockActivity : SimpleConfirmPinLockActivity() {
    override fun isPinCorrect(pin: String): Boolean {
        // Check whether PIN is corrent or not...
        
    }
}
~~~

### Receiving results

if you want to handle results, use ``startActivityForResult()`` , override ``onActivityResult()`` on your activity and receive these result codes.

- ``SimplePinLockActivity.CANCELED`` : PIN input has canceled.
- ``SimplePinLockActivity.PIN_REGISTERED`` PIN has correctly registered
- ``SimplePinLockActivity.PIN_CONFIRMED`` : PIN has confirmed.


## Author

[@aquarla](https://twitter.com/aquarla)

## License

MIT License