# Reactive Microservices

This repository contains a set of reactive microservices designed for the management of consumption reports and bill generation.

## Microservices Overview

### Consumption Report Service

The Consumption Report Service is responsible for managing consumer information and tracking electricity meter readings. It is designed to handle incremental meter readings, where the meter reading increases over time. For example, if a consumer starts with a reading of 5 kW and then uses 10 kW on the next day, the second-day reading will be 15 kW.

#### Features:

- CRUD operations for consumer information.
- Recording and updating electricity meter readings.
- Calculation of consumption based on incremental meter readings.

### Bill Generation Service

The Bill Generation Service is responsible for generating bills for consumers based on their consumption data. It takes consumption reports from the Consumption Report Service and calculates the corresponding bills.

#### Features:

- Retrieval of consumption reports from the Consumption Report Service.
- Bill calculation based on consumption data.
- Generation of bills for consumers.
