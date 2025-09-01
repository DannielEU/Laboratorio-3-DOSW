import java.util.List;

class ConsensusStrategy implements VotingStrategy {
    @Override
    public boolean hasConsensus(List<Integer> votes) {
        return votes.stream().allMatch(v -> v.equals(votes.get(0)));
    }

    @Override
    public int getConsensusValue(List<Integer> votes) {
        return votes.get(0);
    }
}