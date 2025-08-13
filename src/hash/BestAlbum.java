package hash;

import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
//    속한 노래가 많이 재생된 장르를 먼저 수록합니다. -> 장르 count
//    장르 내에서 많이 재생된 노래를 먼저 수록합니다. -> 노래 count
//    장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};	//[4, 1, 3, 0]
        int[] result = solution(genres, plays);


    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotal = new HashMap<>();
        Map<String, List<Music>> byGenre = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreTotal.merge(genres[i], plays[i], Integer::sum);
            byGenre.computeIfAbsent(genres[i], e -> new ArrayList<>())
                    .add(new Music(i, genres[i], plays[i]));
        }

        List<String> genreList = genreTotal.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Integer> answer = new ArrayList<>();

        //정렬 조건
        Comparator<Music> musicComparator = Comparator.comparingInt(Music::getPlays).reversed()
                .thenComparingInt(Music::getIdx);

        for (String genre : genreList) {
            List<Music> musicList = byGenre.get(genre);
            musicList.sort(musicComparator);

            for (int i = 0; i < musicList.size() && i < 2; i++) {
                answer.add(musicList.get(i).getIdx());
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Music {
        private Integer idx;
        private String genre; // 장르
        private Integer plays; // 실행횟수


        public Music(Integer idx, String genre, Integer plays) {
            this.idx = idx;
            this.genre = genre;
            this.plays = plays;
        }

        public Integer getIdx() {
            return idx;
        }

        public Integer getPlays() {
            return plays;
        }
    }
}
