import java.util.List;

interface VotingStrategy {
    boolean hasConsensus(List<Integer> votes);
    int getConsensusValue(List<Integer> votes);
}
