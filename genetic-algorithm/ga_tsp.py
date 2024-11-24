import pandas
import numpy as np
from ga import GA
from ga_tsp_crossovers import order_crossover, pmx_crossover, my_order_crossover
from selection_methods import RouletteWheel, Tournament

np.random.seed(222)

df = pandas.read_csv('cities_distances.csv', index_col='Cidade')
df = df.replace(np.nan, 10000)

cities = df.index
available_cities = cities.drop('Uberaba')
print(df)

class Path(GA.Individual):

    def __init__(self, cities):
        super().__init__(cities)
        self._fitness = None

    @staticmethod
    def random():
        return Path(np.random.permutation(available_cities))

    def fitness(self) -> float:
        if self._fitness is  None:
            fit = [(self.value[i], self.value[i+1]) for i in range(self.value.size - 1)]
            fit.insert(0, ('Uberaba', self.value[0]))
            fit = [df.loc[fit[i]] for i in range(len(fit))]
            self._fitness = - np.sum(fit)
        return self._fitness

    def crossover(self, other: GA.Individual) -> tuple:
        # n = self.value.size
        # pc1 = np.random.randint(int(n *0.05), int(n * 0.51)) + 1
        # child1_value, child2_value = self.__crossover__(self.value, other.value, pc1)
        child1_value, child2_value = my_order_crossover(self.value, other.value)
        # child1_value, child2_value = order_crossover(self.value, other.value)

        return Path(child1_value), Path(child2_value)
    
    def copy(self):
        return Path(np.copy(self.value))

    @staticmethod
    def __crossover__(path1 : np.ndarray, path2 : np.ndarray, pc : int) -> tuple:
        new_path1 : np.ndarray = path1[:pc]
        new_path1 = np.concatenate((new_path1, [city for city in path2 if city not in new_path1]))

        new_path2 : np.ndarray = path2[:pc]
        new_path2 = np.concatenate((new_path2, [city for city in path1 if city not in new_path2])) 
        return new_path1, new_path2

    def mutate(self) -> None:
        i, j = np.random.choice(self.value.size, 2, replace=False)
        self.value[i], self.value[j] = self.value[j], self.value[i]
        self._fitness = None
    
    def __str__(self):
        return f'{self.value} - Fitness: {self.fitness()}'
    
    def __repr__(self):
        return self.__str__()
    
pop_size = 200
initial_pop = [Path.random() for _ in range(pop_size)]

ga = GA(Tournament(150, 50), elitism=True, generations=1000, mut_prob=0.1, crossover_rate=0.75)
best = ga.run(initial_pop)