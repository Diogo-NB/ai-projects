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

    def __init__(self, selection: SelectionMethod, elitism: bool = False, generations: int = 1000, mut_prob: float = 0.05):
        self.selection = selection
        self.elitism = elitism
        self.generations = generations
        self.mut_prob = mut_prob

    def __str__(self):
        return f"GA[selection: {self.selection}, elitism: {self.elitism}]"

    def run(self, initial_pop : list[Individual], generations: int = None, mut_prob: float = None):
        if generations is None:
            generations = self.generations
        if mut_prob is None:
            mut_prob = self.mut_prob
            
        pop : list[GA.Individual] = np.array(initial_pop, dtype=GA.Individual)
        best_result = pop[0].value

        for _ in range(generations):
            fitness : np.ndarray = np.array([ind.fitness() for ind in pop])

            if self.elitism:
                pop[np.argmin(fitness)].value = best_result

            best_result = pop[np.argmax(fitness)].value

            selected : list[GA.Individual] = self.selection.select(fitness, pop)

            next_pop : list[GA.Individual] = []

            for i in range(0, len(pop), 2):
                parent1, parent2 = np.random.choice(selected, 2, replace=False)

                child1, child2 = parent1.crossover(parent2)

                if np.random.random() <= mut_prob:
                    child1.mutate()
                if np.random.random() <= mut_prob:
                    child2.mutate()

                next_pop.append(child1)
                next_pop.append(child2)

            pop = np.array(next_pop, dtype=GA.Individual)

        return pop[np.argmax(fitness)].value