package detekt.issues.dummy.ignore.me

fun unsafeSubstring(s: String?): String? {
    return s!!.substring(0, 5)
}
