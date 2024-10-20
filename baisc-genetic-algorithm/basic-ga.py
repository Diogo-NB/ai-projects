import numpy as np

class GA:

    @staticmethod
    def mutate(cr):
        r = np.random.randint(0, 9)  

        mask : np.uint16 = 1 << r           

        new_cr : np.uint16 = cr ^ mask # XOR   

        return new_cr

    @staticmethod
    def crossover(cr1 : np.uint16, cr2 : np.uint16, pc : np.uint16):
        aux_b = pc
        aux_a = 65535 - pc

        cr1_a = cr1 & aux_a
        cr1_b = cr1 & aux_b

        cr2_a = cr2 & aux_a
        cr2_b = cr2 & aux_b

        new_cr1 = cr1_a | cr2_b
        new_cr2 = cr2_a | cr1_b

        return new_cr1, new_cr2

    @staticmethod
    def roulette_wheel_selection(f, pop, selection_size):
        f_sum = np.sum(f)

        p = f / f_sum

        p_acum = np.zeros_like(p)
        p_acum[0] = p[0]

        for i in range(1, len(pop)):
            p_acum[i] = p_acum[i - 1] + p[i]

        selection = []
        for pr in np.random.uniform(0.0, 1.0, selection_size):
            i = 0

            while p_acum[i] < pr:
                i+=1

            selection.append(pop[i]) 

        return selection

    @staticmethod
    def find_max(f, n_pop, p_mut = 0.05, min_repeats_count = 50):
        epochs = 0

        pop = np.random.randint(0, 512, size=n_pop, dtype=np.uint16)

        best_individual = pop[0]

        repeats_count = 0

        print(f"{pop=}")
        while repeats_count < min_repeats_count:
            epochs += 1

            fitness : np.ndarray = f(pop)
            prev_best_individual = best_individual
            best_individual = max(pop, key=f)

            if prev_best_individual == best_individual:
                repeats_count += 1
            else:
                repeats_count = 0

            pop = GA.roulette_wheel_selection(fitness, pop, n_pop)

            pc = int(np.floor(np.random.uniform(0.05, 0.51)))

            next_pop = []
            for i in range(0, len(pop), 2):
                parent1 = pop[i]
                parent2 = pop[i + 1]

                child1, child2 = GA.crossover(parent1, parent2, pc)

                if np.random.random() <= p_mut:
                    child1 = GA.mutate(child1)
                if np.random.random() <= p_mut:
                    child2 = GA.mutate(child2) 

                next_pop.append(child1)
                next_pop.append(child2)  

            pop = np.array(next_pop, dtype=np.uint16)

            print(f"{best_individual=}")
            pop[0] = best_individual

        print(f"{pop=}")

        return best_individual
    
g = lambda x: - np.abs(x * np.sin(np.sqrt(np.abs(x))))
f = lambda x: - g(x)

n = 100 # population size

x = GA.find_max(f, n)

print(f" {x=} {g(x)=}")
