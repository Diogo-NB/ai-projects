import pandas
import numpy as np
from ga import GA
from selection_methods import RouletteWheel

df = pandas.read_csv('cities_distances.csv', index_col='Cidade')
invalid_path_size = np.finfo(np.float32).max
df = df.replace(np.nan, invalid_path_size)

cities = df.index
available_cities = cities.drop('Uberaba')

class Path(GA.Individual):

    def __init__(self, cities):
        super().__init__(cities)
        self._fitness = None

    @staticmethod
    def random():
        return Path(np.random.permutation(available_cities))

    def fitness(self) -> float:
        if self._fitness is  None:
            cities_pairs = [(self.value[i], self.value[i+1]) for i in range(self.value.size - 1)]
            cities_pairs.insert(0, ('Uberaba', self.value[0]))
            fitness = [df.loc[cities_pair] for cities_pair in cities_pairs]
           
            self._fitness = - np.min([np.sum(fitness), invalid_path_size])

        return self._fitness

    def crossover(self, other: GA.Individual) -> tuple:
        n = self.value.size
        
        pcs = sorted(np.random.choice(n, 2, replace=False)) # pc1, pc2

        child1_value, child2_value = self.__paths_crossover__(self.value, other.value, pcs)

        return Path(child1_value), Path(child2_value)

    @staticmethod
    def __paths_crossover__(path1 : np.ndarray, path2 : np.ndarray, pcs: tuple[int]) -> tuple:
        pc1, pc2 = pcs
        new_path1 = path1[pc1:pc2]
        new_path2 = path2[pc1:pc2]

        path1_aux = [city for city in path1 if city not in new_path2]
        path2_aux = [city for city in path2 if city not in new_path1]
        
        new_path1 = np.concatenate((path2_aux[:pc1], new_path1, path2_aux[pc1:]))
        new_path2 = np.concatenate((path1_aux[:pc1], new_path2, path1_aux[pc1:]))

        return new_path1, new_path2
    
    def copy(self):
        return Path(np.copy(self.value))

    def mutate(self) -> None:
        i, j = np.random.choice(self.value.size, 2, replace=False)
        self.value[i], self.value[j] = self.value[j], self.value[i]
        self._fitness = None
    
    def __str__(self):
        fitness = - self.fitness()

        if fitness >= invalid_path_size:
            return 'Invalid'

        return f'{fitness}'
    
    def __repr__(self):
        return self.__str__()
    
pop_size = 125
create_pop = lambda: [Path.random() for _ in range(pop_size)]

ga = GA(RouletteWheel(90), elitism=True, generations=222, mut_rate=0.1, crossover_rate=0.75)
# best = ga.run(create_pop(), generations=2000)
best = ga.multi_run([create_pop() for _ in range(3)], epochs=3, carryover_rate=0.1)
cities_list = np.concatenate((['Uberaba'], best.value))
cities_pairs = [(cities_list[i], cities_list[i+1]) for i in range(cities_list.size - 1)]

distances = [df.loc[cities_pair] for cities_pair in cities_pairs]

for i, distance in enumerate(distances):
    print(f'{cities_pairs[i]}: {distance}')

print(f'Best path: {best.value} length: {-best.fitness()}')