# Ecosystem Simulation 🌱

## Выполнил

Короткевич Алексей

## Использование паттернов

- Iterator  
Использовался для прохождения по коллеккции из Plants для вывода информации в консоль.  
Интерфейсы(Iterator, Iterable) можно найти в пакете model.  
Классы(PlantIterator, PlantCollection) можно найти в пакете model/plant.  
Сам Итератор использовался в пакете view в классе ConsoleView.  

- State  
Использовался для изменения свойств экосистемы в зависимости от состояния экосистемы.  
Интерфейс(EcosystemState) и его реализации(DryEcosystemState, HotEcosystemState, WetEcosystemState) находятся в пакете  simulation/ecosystem.  
Само состояние находится в классе Ecosystem и меняется в случайном порядке.  

## Описание проекта

Симулятор экосистемы — это приложение, которое позволяет пользователям управлять и исследовать взаимодействия между различными видами растений и животных в виртуальной экосистеме. Приложение моделирует сложные экосистемные процессы, включая пищевые цепи, климатические условия и ресурсы, необходимые для существования видов.

## Возможности

- **CRUD-операции**: Добавление, редактирование и удаление объектов экосистемы (растений и животных). Все данные хранятся в текстовых файлах (.txt), что позволяет пользователю легко управлять информацией.

- **Управление симуляциями**: Пользователь может продолжить работу с существующими симуляциями или создать новую. Каждая симуляция сохраняется отдельно, чтобы избежать перезаписи данных.

- **Настройка экосистемы**: Возможность задания параметров экосистемы, таких как температура, влажность, доступность воды, влияющие на жизнедеятельность видов.

- **Прогнозирование**: Приложение предоставляет прогнозы о популяциях видов — какие из них будут увеличиваться, уменьшаться или оставаться стабильными в зависимости от текущих климатических и ресурсных параметров.

- **Автоматическая симуляция**: Экосистема может развиваться самостоятельно. Все изменения, включая динамику популяций и климатические условия, записываются в лог-файл (.txt), что позволяет отслеживать ход симуляции.

## Технологии

- Java 17
- Gradle
- Lombok
- Logback

## Инструкция по запуску проекта
1. Склонируйте репозиторий на свой компьютер 💻
  ```bash
  git clone https://github.com/LyeshaKorotkevich/senla-task-simulation.git
  ```
2. Установка и запуск
  * Для Linux/Mac 🍎:
    ```bash
    ./gradlew shadowJar
    ./run.sh
    ```
  * Для Windows 🪟:
    ```bash
    gradlew shadowJar
    run.bat
    ```

## Работа приложения

- Главное меню программы:
```
1. Create new simulation 
2. Load simulation
```

- Выбор уже созданной симуляции:
```
1. Create new simulation 
2. Load simulation
2
Select a directory to load the simulation:
1. simulation20241029_2307
2. simulation20241029_2355
3. defaultSimulation
4. simulation20241029_2258
```

- Меню для работы с симуляцией:
```
Simulation loaded from directory: defaultSimulation
Welcome to the Ecosystem Simulator!

Enter 1 to simulate, 2 to predict, 3 to configure the environment, 4 to add a creature, 5 to remove a creature, 6 to update a creature, or 0 to exit:
```

### CRUD операции

- Добавление сущности(животного)
```
4
Choose the type to add (1 - Animal, 2 - Plant): 
1
Enter the animal's name: 
wolf
Enter animal type (Herbivore/Predator/Omnivore): 
predator
Enter the animal's consumption rate: 
3
wolf has been added as an Animal.
Current ecosystem status:
Ecosystem resources: EcosystemResources{temperature=25.00, humidity=50.00%, availableWater=100.00L}
Animals:
...
Crow (ID: 20): Diet: Omnivore, Consumption Rate: 4
wolf (ID: 21): Diet: Carnivore, Consumption Rate: 3
```

- Изменение сущности(животного)
```
4
Choose the type to add (1 - Animal, 2 - Plant): 
1
6
Choose the type to update (1 - Animal, 2 - Plant): 
1
Enter the ID of the entity to update: 
21
Enter the new name for the animal: 
wolf
Enter the new consumption rate for the animal: 
5
Animal updated successfully.
Current ecosystem status:
Ecosystem resources: EcosystemResources{temperature=25.00, humidity=50.00%, availableWater=100.00L}
Animals:
...
Crow (ID: 20): Diet: Omnivore, Consumption Rate: 4
wolf (ID: 21): Diet: Carnivore, Consumption Rate: 5
```

- Удаление сущности(животного)
```
5
Choose the type to delete (1 - Animal, 2 - Plant): 
1
Enter the ID of the entity to delete: 
21
Animal with ID 21 has been deleted.
Current ecosystem status:
Ecosystem resources: EcosystemResources{temperature=25.00, humidity=50.00%, availableWater=100.00L}
Animals:
...
Crow (ID: 20): Diet: Omnivore, Consumption Rate: 4
```

### Настройка экосистемы

- Экосистема настраивается случайно
```
3
Enter 1 to simulate, 2 to predict, 3 to configure the environment, 4 to add a creature, 5 to remove a creature, 6 to update a creature, or 0 to exit:
Environment adjusted.
Current ecosystem status:
Ecosystem resources: EcosystemResources{temperature=25.76, humidity=50.54%, availableWater=98.00L}
```
### Прогнозирование

```
Enter 1 to simulate, 2 to predict, 3 to configure the environment, 4 to add a creature, 5 to remove a creature, 6 to update a creature, or 0 to exit:
2
Population Trends Prediction:
Deer population is likely to increase.
Lion population is likely to increase.
Rabbit population is likely to increase.
Wolf population is likely to increase.
Bear population is likely to decrease.
Tiger population is likely to increase.
Giraffe population is likely to increase.
Cheetah population is likely to increase.
Fox population is likely to decrease.
Eagle population is likely to increase.
Zebra population is likely to increase.
GrizzlyBear population is likely to increase.
Raccoon population is likely to decrease.
Leopard population is likely to increase.
Panda population is likely to increase.
Boar population is likely to decrease.
Hippopotamus population is likely to increase.
Falcon population is likely to increase.
Bison population is likely to increase.
Crow population is likely to decrease.
Rose population is stable.
Tulip population is stable.
```

### Автоматическая симуляция

```
Enter 1 to simulate, 2 to predict, 3 to configure the environment, 4 to add a creature, 5 to remove a creature, 6 to update a creature, or 0 to exit:
1
Starting ecosystem simulation...
Rabbit flees from Cheetah
Rabbit runs away!
Rabbit flees from GrizzlyBear
Rabbit runs away!
Tiger attacks Boar, considering it as food.
Tiger is eating Boar
Tiger is not interested in Eagle
Leopard attacks Bear, considering it as food.
Leopard is eating Bear
Leopard attacks Raccoon, considering it as food.
Leopard is eating Raccoon
Crow ignores Hippopotamus
Crow decides to eat Rabbit
Crow is eating Rabbit
Eagle chases and eats Panda
Eagle is eating Panda
Eagle is not interested in Lion
Lion is not interested in Wolf
Lion is not interested in Crow
Hippopotamus continues grazing and ignores Deer
Hippopotamus flees from Lion
Hippopotamus runs away!
Bison continues grazing and ignores Crow
Bison flees from Cheetah
Bison runs away!
Deer continues grazing and ignores Cheetah
Deer flees from Leopard
Deer runs away!
Wolf is not interested in GrizzlyBear
Wolf is not interested in Leopard
Zebra continues grazing and ignores Hippopotamus
Zebra continues grazing and ignores Crow
Giraffe continues grazing and ignores Falcon
Giraffe flees from Tiger
Giraffe runs away!
Fox ignores GrizzlyBear
Fox decides to eat Bison
Fox is eating Bison
Cheetah is not interested in Zebra
Cheetah is not interested in GrizzlyBear
Falcon chases and eats Hippopotamus
Falcon is eating Hippopotamus
Falcon is not interested in GrizzlyBear
GrizzlyBear is not interested in Falcon
GrizzlyBear is not interested in Wolf
Rose is thriving.
Tulip is thriving.

--- Ecosystem Simulation Summary ---
Surviving Animals: 13
Surviving Plants: 2
Ecosystem simulated successfully.
Current ecosystem status:
Ecosystem resources: EcosystemResources{temperature=24.04, humidity=50.34%, availableWater=148.00L}
Animals:
Deer (ID: 1): Diet: Herbivore, Consumption Rate: 3
Lion (ID: 2): Diet: Carnivore, Consumption Rate: 5
Wolf (ID: 4): Diet: Carnivore, Consumption Rate: 5
Tiger (ID: 6): Diet: Carnivore, Consumption Rate: 5
Giraffe (ID: 7): Diet: Herbivore, Consumption Rate: 3
Cheetah (ID: 8): Diet: Carnivore, Consumption Rate: 5
Fox (ID: 9): Diet: Omnivore, Consumption Rate: 4
Eagle (ID: 10): Diet: Carnivore, Consumption Rate: 5
Zebra (ID: 11): Diet: Herbivore, Consumption Rate: 3
GrizzlyBear (ID: 12): Diet: Carnivore, Consumption Rate: 5
Leopard (ID: 14): Diet: Carnivore, Consumption Rate: 5
Falcon (ID: 18): Diet: Carnivore, Consumption Rate: 5
Crow (ID: 20): Diet: Omnivore, Consumption Rate: 4
Plants:
Rose (ID: 1): Growth Conditions: Unknown
Tulip (ID: 2): Growth Conditions: Unknown
```
