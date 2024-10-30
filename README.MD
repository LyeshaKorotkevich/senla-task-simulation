# Ecosystem Simulation 🌱

## Выполнил

Короткевич Алексей

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
- Lombok
- Logback

## Инструкция по запуску проекта
* Склонируйте репозиторий себе на компьютер
* Соберите проект с помощью Gradle:
  ```bash
  ./gradlew shadowJar
  ```
* Запустите run.sh если у вас Linux/Unix
  ```bash
  ./run.sh
  ```
* Запустите run.bat если у вас Windows
  ```bash
  ./run.bat
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
FloweringPlant population is likely to increase.
FloweringPlant population is likely to increase.
```

### Автоматическая симуляция

```
Enter 1 to simulate, 2 to predict, 3 to configure the environment, 4 to add a creature, 5 to remove a creature, 6 to update a creature, or 0 to exit:
1
Tiger is eating Giraffe
Bison is eating FloweringPlant
Wolf is eating Bison
Cheetah is eating Panda
Cheetah is eating Giraffe
FloweringPlant is thriving.
FloweringPlant is thriving.
Starting ecosystem simulation...
Deer flees from Tiger
Deer runs away!
Deer continues grazing and ignores Bear
Lion is not interested in Raccoon
Lion is not interested in Zebra
Rabbit flees from Tiger
Rabbit runs away!
Rabbit continues grazing and ignores Boar
Wolf chases and eats Deer
Wolf is eating Deer
Wolf is not interested in Falcon
Bear ignores Leopard
Bear ignores Raccoon
Tiger is not interested in Falcon
Tiger attacks Raccoon, considering it as food.
Tiger is eating Raccoon
Cheetah chases and eats Rabbit
Cheetah is eating Rabbit
Cheetah is not interested in Raccoon
Fox decides to eat Rabbit
Fox is eating Rabbit
Fox ignores Raccoon
Eagle is not interested in Rabbit
Eagle is not interested in Crow
Zebra flees from Eagle
Zebra runs away!
Zebra flees from Cheetah
Zebra runs away!
GrizzlyBear chases and eats Rabbit
GrizzlyBear is eating Rabbit
GrizzlyBear is not interested in Wolf
Raccoon ignores Deer
Raccoon ignores Rabbit
Leopard attacks Raccoon, considering it as food.
Leopard is eating Raccoon
Leopard is not interested in Wolf
Boar ignores Zebra
Boar ignores Hippopotamus
Hippopotamus continues grazing and ignores Rabbit
Hippopotamus continues grazing and ignores Boar
Falcon is not interested in Lion
Falcon is not interested in Leopard
Crow ignores Leopard
Crow cautiously interacts with Eagle
FloweringPlant is thriving.
Deer has been removed from the simulation.
Raccoon has been removed from the simulation.
Rabbit has been removed from the simulation.
Rabbit has been removed from the simulation.
Rabbit has been removed from the simulation.
Raccoon has been removed from the simulation.

--- Ecosystem Simulation Summary ---
Surviving Animals: 11
Surviving Plants: 1
Animals removed: 6
Ecosystem simulated successfully.
Current ecosystem status:
Ecosystem resources: EcosystemResources{temperature=25.30, humidity=51.24%, availableWater=146.00L}
Animals:
Lion (ID: 2): Diet: Carnivore, Consumption Rate: 5
Wolf (ID: 4): Diet: Carnivore, Consumption Rate: 5
Bear (ID: 5): Diet: Omnivore, Consumption Rate: 4
Tiger (ID: 6): Diet: Carnivore, Consumption Rate: 5
Cheetah (ID: 8): Diet: Carnivore, Consumption Rate: 5
Fox (ID: 9): Diet: Omnivore, Consumption Rate: 4
Eagle (ID: 10): Diet: Carnivore, Consumption Rate: 5
Zebra (ID: 11): Diet: Herbivore, Consumption Rate: 3
GrizzlyBear (ID: 12): Diet: Carnivore, Consumption Rate: 5
Leopard (ID: 14): Diet: Carnivore, Consumption Rate: 5
Boar (ID: 16): Diet: Omnivore, Consumption Rate: 4
Hippopotamus (ID: 17): Diet: Herbivore, Consumption Rate: 3
Falcon (ID: 18): Diet: Carnivore, Consumption Rate: 5
Crow (ID: 20): Diet: Omnivore, Consumption Rate: 4
Plants:
FloweringPlant (ID: 1): Growth Conditions: Rose
```