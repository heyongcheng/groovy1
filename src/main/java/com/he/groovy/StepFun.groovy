package com.he.groovy

/**
 * @author heyc
 * @date 2018/4/13 16:42
 */
class StepFun {

    def step(n) {
        if (n < 1) {
            return 0
        }
        if (n == 1) {
            return 1
        }
        if (n == 2) {
            return 2
        }
        if (n == 3) {
            return 4
        }
        return step(n - 1) + step(n - 2) + step(n - 3)
    }

    static void main(String[] args) {
        println new StepFun().step(10)
    }

}
