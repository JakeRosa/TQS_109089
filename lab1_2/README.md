## Lab1.2 - Answers

### 2c) Which classes/methods offer less coverage? Are all possible [decision] branches being covered?

The class with the least coverage is CuponEuromillions, with 34% coverage in Instructions and 0% coverage in Branches. Its methods with the least converage are countDips(), which returns the size of the Dips collection, and format(), which returns a formatted string with the Dips' information. The absence of converage in this case doesn't seem to be critical.

![](images/Screenshot%20from%202024-02-19%2013-10-46.png)
![](images/Screenshot%20from%202024-02-19%2013-10-58.png)

The other class with less converage is BoundedSetOfNaturals, which has 54% coverage in Instructions and 50% coverage in Branches, as we can see in the images below:

![](images/Screenshot%20from%202024-02-19%2013-11-52.png)
![](images/Screenshot%20from%202024-02-19%2013-11-58.png)

#### Tests that should be written:
- Test add method when a set is already full
- Test add method with a element that was already added to the set
- Test add method with a negative number
- Test create a set with a invalid array (non-natural numbers)
- Test intersection between sets
- Test equals method with two equal sets
- Test equals method with null object
- Test equals method with different class types
- Test hashcode with two equals sets

### 2d) Run Jacoco coverage analysis and compare with previous results.

### After
![](images/Screenshot%20from%202024-02-19%2014-35-21.png)
![](images/Screenshot%20from%202024-02-19%2014-35-27.png)