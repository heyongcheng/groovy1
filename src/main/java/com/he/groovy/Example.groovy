package com.he.groovy

/**
 * @author heyc
 * @date 2018/4/8 11:44
 */
class Example {

    static void main(String[] args) {
        def name = 'song';
        def range = 5..10;

        println("hello groovy:" + name)
        println(range)
        println(range.get(0))

        println(sum(2,6))

        def exam = new Example();
        def multi = exam.multi(2)
        println(multi)

        exam.closerCall()
    }

    def static sum(a, b = 1) {
        return a + b;
    }

    def multi(a, b = 1) {
        return  a - b;
    }

    def closerCall() {
        def closer = {println "hello ${it}"};
        closer.call('world');
    }



}
