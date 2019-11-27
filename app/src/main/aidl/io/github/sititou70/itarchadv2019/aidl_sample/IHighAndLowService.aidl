// IHighAndLowService.aidl
package io.github.sititou70.itarchadv2019.aidl_sample;

// Declare any non-default types here with import statements

interface IHighAndLowService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String check(int number);
    void randomize();
}
