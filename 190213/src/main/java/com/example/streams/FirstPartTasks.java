package com.example.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FirstPartTasks {

    private FirstPartTasks() {}

    // Список названий альбомов
    public static List<String> allNames(Stream<Album> albums) {
        return albums.map(Album::getName).collect(Collectors.toList());
    }

    // Список названий альбомов, отсортированный лексикографически по названию
    public static List<String> allNamesSorted(Stream<Album> albums) {
        return albums.map(Album::getName).sorted().collect(Collectors.toList());
    }

    // Список треков, отсортированный лексикографически по названию, включающий все треки альбомов из 'albums'
    public static List<String> allTracksSorted(Stream<Album> albums) {
        return albums.flatMap(album -> album.getTracks().stream()).map(Track::getName).sorted().collect(Collectors.toList());
    }

    // Список альбомов, в которых есть хотя бы один трек с рейтингом более 95, отсортированный по названию
    public static List<Album> sortedFavorites(Stream<Album> s) {
        return s.filter(
                album ->
                        album
                                .getTracks()
                                .stream()
                                .anyMatch(track -> (track.getRating() > 95)))
                .sorted(Comparator.comparing(Album::getName)).collect(Collectors.toList());
    }

    // Сгруппировать альбомы по артистам
    public static Map<Artist, List<Album>> groupByArtist(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getArtist));
    }

    // Сгруппировать альбомы по артистам (в качестве значения вместо объекта 'Artist' использовать его имя)
    public static Map<Artist, List<String>> groupByArtistMapName(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Album::getArtist, Collectors.mapping(Album::getName, Collectors.toList())));
    }

    // Число повторяющихся альбомов в потоке
    public static long countAlbumDuplicates(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter( p -> p.getValue() > 1 ).count();
    }

    // Альбом, в котором максимум рейтинга минимален
    // (если в альбоме нет ни одного трека, считать, что максимум рейтинга в нем --- 0)
    public static Optional<Album> minMaxRating(Stream<Album> albums) {
        return albums.min((first, second) -> {
            var firstMax = findListMax(first.getTracks(), Comparator.comparing(Track::getRating));
            var secondMax = findListMax(second.getTracks(), Comparator.comparing(Track::getRating));
            if (firstMax.isEmpty()) {
                return secondMax.map(Track::getRating).orElse(0);
            }
            if (secondMax.isEmpty()) {
                return firstMax.map(Track::getRating).orElse(0);
            }
            return Integer.compare(firstMax.get().getRating(), secondMax.get().getRating());
        });
    }

    private static <U> Optional<U> findListMax(List<U> list, Comparator<? super U> comparator) {
        return list.stream().max(comparator);
    }

    // Список альбомов, отсортированный по убыванию среднего рейтинга его треков (0, если треков нет)
    public static List<Album> sortByAverageRating(Stream<Album> albums) {
        return albums
                .sorted(
                        (album1, album2) ->
                                (int)(- (double)(album1.getTracks().stream().mapToInt(Track::getRating).sum() / album1.getTracks().size())
                                        + (double)(album2.getTracks().stream().mapToInt(Track::getRating).sum() / album2.getTracks().size())))
                .collect(Collectors.toList());
    }

    // Произведение всех чисел потока по модулю 'modulo'
    // (все числа от 0 до 10000)
    public static int moduloProduction(IntStream stream, int modulo) {
        return stream.reduce(1, (a, b) -> (a * b) % modulo);
    }

    // Вернуть строку, состояющую из конкатенаций переданного массива, и окруженную строками "<", ">"
    // см. тесты
    public static String joinTo(String... strings) {
        return Arrays.stream(strings).collect(Collectors.joining(", ", "<", ">"));
    }

    // Вернуть поток из объектов класса 'clazz'
    public static <R> Stream<R> filterIsInstance(Stream<?> s, Class<R> clazz) {
        return s
                .filter(clazz::isInstance)
                .map(t -> (R) t);
    }
}