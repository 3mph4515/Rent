package io.flowerpower.rent.model;

/**
 * Created by Andrew Kuksov on 6/9/16.
 */
interface A {
    void test();
}

interface B {
    void test();
}

class C implements A, B {

    @Override
    public void test() {

    }

}

