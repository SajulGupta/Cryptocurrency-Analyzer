# Cryptocurrency-Analyzer
## About


## Requirements
Nomics API - For fetching data

Jfree Chart – For visualizing data into chart.

Simple json – For converting API data into json format.

## Usage
First clone the Repository on your local computer
```bash
git clone https://github.com/SajulGupta/Cryptocurrency-Analyzer.git

```
Then, replace [`[YOUR-API-KEY]`] with your own API key in GraphPanel.java file
```text
static String api_key = "Your API Key";
```
Then, Compile and Run the Program
```bash
javac -classpath jfreechart-1.5.3.jar:json-simple-1.1.1.jar:. GraphPanel.java >/dev/null 2>&1
java -classpath jfreechart-1.5.3.jar:json-simple-1.1.1.jar:. GraphPanel

```
