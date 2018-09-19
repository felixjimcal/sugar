# Sugar Calculator
Android application to check the sugar in processed products

## Getting Started

It's an android studio project, built with distro 21.

### Prerequisites

Read "getting started

```
AndroidStudio
```

### Installing

Install Android Studio for debbug. Go to PlayStore to download (https://play.google.com/store/apps/details?id=cat.zarpa.felix.sugar)

## Authors

* Felix Jimenez Calvo - *Initial work* - [calibre93](https://github.com/calibre93)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* My daily life practice, Code Complete 2, StackOverFlow.








# Sugar Calculator with TDD
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need Android Studio to run the code and test, distro 21.
https://developer.android.com/studio/


## Running the tests

To run the test just open it with the IDE and press "run all test" button

### Break down into end to end tests
The test shows you a expected result for every collaborator, transforming the data, calculating the amount, etc.

For example in test
```
	public class TransformatorShould {
    @Test
    @Parameters({
            "1, 0, 0.001", // Mililiters
            "1000, 0, 1", // Mililiters
            "1, 1, 0.01", // Centiliters
            "1000, 1, 10" // Centiliters
    })
    public void TransformUnitToBaseUnit(double quantity, int id, double expected)
    {
        double base_unit = Transformator.transformUnitToBaseUnit(quantity, id);
        Assert.assertThat(expected, is(base_unit));
    }

}
```

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Fèlix Jiménez Calvo** - *Initial work* - [felixjimcal](https://github.com/felixjimcal)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* The people who challenge me to improve my knowledge
