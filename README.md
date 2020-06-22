# Raspberry Pi Thermostat

### Dependencies

- Java
- Raspberry Pi 2 B+ or above
- DHT11 Temperature & Humidity Sensor
- 4 Port Relay Module
- Raspberry Pi Camera

##### Run
```sh 
$ java -jar Raspberry-Pi-Climate-Control.jar (Verbose Logging (True/False)) (Web Server Port (8443))

```

##### Run at Startup (Optional)
Add the run command to your [RC.LOCAL](https://www.raspberrypi.org/documentation/linux/usage/rc-local.md) file. Don't forget to add a "&" after the command so it runs in the background.

### Credits

[Eric Smith](https://stackoverflow.com/questions/28486159/read-temperature-from-dht11-using-pi4j/34976602#34976602) - For writing most of the [DHT11.java](https://github.com/MarcWoodyard/Raspberry-Pi-Climate-Control/blob/master/src/sensors/DHT11.java) code.

### Troubleshooting

> Unable to determine hardware version. I see: Hardware: BCM2835

https://github.com/Pi4J/pi4j/issues/319 

**Solution :** 
```sh 
sudo rpi-update 52241088c1da59a359110d39c1875cda56496764
```

