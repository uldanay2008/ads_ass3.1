import java.util.Arrays;

public class Experiment {
    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();

    // Измерение времени сортировки
    public long measureSortTime(int[] arr, String type) {
        long startTime = System.nanoTime();
        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(arr);
        } else if (type.equalsIgnoreCase("advanced")) {
            sorter.advancedSort(arr);
        }
        return System.nanoTime() - startTime;
    }

    // Измерение времени поиска
    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        return System.nanoTime() - startTime;
    }

    // Запуск всех экспериментов согласно заданию
    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};

        System.out.println("Size | Algorithm | Array Type | Time (ns)");
        System.out.println("-----------------------------------------");

        for (int size : sizes) {
            int[] randomArr = sorter.generateRandomArray(size);

            // Selection Sort
            long basicRand = measureSortTime(randomArr.clone(), "basic");
            System.out.printf("%-4d | Basic Sort | Random     | %d\n", size, basicRand);

            // Quick Sort
            long advRand = measureSortTime(randomArr.clone(), "advanced");
            System.out.printf("%-4d | Adv. Sort  | Random     | %d\n", size, advRand);

            // Поиск (на отсортированном массиве)
            int[] sortedArr = randomArr.clone();
            Arrays.sort(sortedArr);
            int target = sortedArr[size / 2];
            long searchTime = measureSearchTime(sortedArr, target);
            System.out.printf("%-4d | Binary Srch| Sorted     | %d\n", size, searchTime);

            // Тест Advanced Sort на уже отсортированных данных
            long advSorted = measureSortTime(sortedArr.clone(), "advanced");
            System.out.printf("%-4d | Adv. Sort  | Sorted     | %d\n", size, advSorted);
            System.out.println("-----------------------------------------");
        }
    }
}