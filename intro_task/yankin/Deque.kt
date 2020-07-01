class DequeElement<T>(val value : T,
                      var prev : DequeElement<T>? = null,
                      var next : DequeElement<T>? = null)

class Deque<T> {
    private var first : DequeElement<T>? = null
    private var last : DequeElement<T>? = null


    fun front() : T? =  first?.value

    fun back() : T? =  last?.value

    fun isEmpty() = first == null

    fun size() : Int {
        if (isEmpty()) return 0

        var size = 1
        var node = first
        while (node != last) {
            size++
            node = node!!.next
        }
        return size
    }

    fun clear() {
        while (!isEmpty()) {
            val node = first
            first = first!!.next
            node!!.next = null
            first?.prev = null
        }
        last = null
    }

    fun pushFront(value : T) {
        if (!isEmpty()) {
            val newNode = DequeElement(value, next = first)
            first!!.prev = newNode
            first = newNode
        } else  {
            first = DequeElement(value)
            last = first
        }
    }

    fun pushBack(value : T) {
        if (!isEmpty()) {
            val newNode = DequeElement(value, prev = last)
            last!!.next = newNode
            last = newNode
        } else  {
            last = DequeElement(value)
            first = last
        }
    }

    fun popFront() : T? {
        val resNode = first
        if (!isEmpty()) {
            val node = first
            first = first!!.next
            node!!.next = null
            first?.prev = null

            if (first == null) last = null
        }
        return resNode?.value
    }

    fun popBack() : T? {
        val resNode = last
        if (!isEmpty()) {
            val node = last
            last = last!!.prev
            node!!.prev = null
            last?.next = null

            if (last == null) first = null
        }
        return resNode?.value
    }
}