package detekt.issues.dummy

import detekt.issues.dummy.ignore.me.unsafeSubstring

fun useUnsafeSubstring() {
    val unsafeCallResult = unsafeSubstring("detektTest")
    print(unsafeCallResult!!.capitalize())
}
