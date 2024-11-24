import numpy as np
from selection_methods import SelectionMethod

class GA:

    class Individual:

        def __init__(self, value):
            self.value = value

        def fitness(self) -> float:
            raise NotImplementedError

        def crossover(self, other) -> tuple:
            raise NotImplementedError
        
        def mutate(self) -> None:
            raise NotImplementedError
        
        def copy(self):
            return NotImplementedError

    def __init__(self, selection: SelectionMethod, elitism: bool = False, generations: int = 1000, mut_rate: float = 0.05, crossover_rate: float = 0.5):
        self.selection = selection
        self.elitism = elitism
        self.generations = generations
        self.mut_rate = mut_rate
        self.crossover_rate = crossover_rate

    def __str__(self):
        return f"GA[selection: {self.selection}, elitism: {self.elitism}]"

    def run(self, initial_pop : list[Individual], generations: int = None, mut_rate: float = None, crossover_rate: float = None, elitism: bool = None) -> Individual:
        if generations is None:
            generations = self.generations
        if mut_rate is None:
            mut_rate = self.mut_rate
        if crossover_rate is None:
            crossover_rate = self.crossover_rate
        if elitism is None:
            elitism = self.elitism
            
        pop : np.ndarray[GA.Individual] = np.array(initial_pop, dtype=GA.Individual)

        for _ in range(generations):
            fitness : np.ndarray = np.array([ind.fitness() for ind in pop])
            best = pop[np.argmax(fitness)].copy()
            print(f"Generation {_} - Best: {best}")

            selected = self.selection.select(fitness, pop)

            children : list[GA.Individual] = []

            crossover_size = int(pop.size * crossover_rate)
            for _ in range(0, crossover_size, 2):
                parent1, parent2 = np.random.choice(selected, 2, replace=False)

                child1, child2 = parent1.crossover(parent2)

                children.append(child1)
                children.append(child2)

            carryover_size = pop.size - crossover_size
            pop = np.concatenate((selected[-carryover_size:], children[:crossover_size]), dtype=GA.Individual)

            mutation_selected = np.random.choice(pop, int(pop.size * mut_rate), replace=False)
            for ind in mutation_selected:
                ind.mutate()

            if elitism:
                pop[np.random.randint(pop.size)] = best

        print(pop)

        return best