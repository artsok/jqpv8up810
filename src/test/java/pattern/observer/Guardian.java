package pattern.observer;

class Guardian implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet more news in London... " + tweet);
        }
    }
}
