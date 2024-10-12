class LazyProperty(val initializer: () -> Int) {
    /* TODO */
    private var _lazy: Int? = null

    val lazy: Int
        get() {
            if (_lazy == null) {
                _lazy = initializer()
            }
            return _lazy!!
        }
}
