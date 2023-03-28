package com.example.cs5520_inclass_tanvi8146;

public class ChatModel {

    private String chatId;
    private String lastMessage;
    private String displaySender;
    private String displayReceiver;
    private long time;

    public ChatModel() {
    }

    public ChatModel(String chatId, String lastMessage, String displaySender, String displayReceiver, long time) {
        this.chatId = chatId;
        this.lastMessage = lastMessage;
        this.displaySender = displaySender;
        this.displayReceiver = displayReceiver;
        this.time = time;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getDisplaySender() {
        return displaySender;
    }

    public void setDisplaySender(String displaySender) {
        this.displaySender = displaySender;
    }

    public String getDisplayReceiver() {
        return displayReceiver;
    }

    public void setDisplayReceiver(String displayReceiver) {
        this.displayReceiver = displayReceiver;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}


//
//
//public class ChatListActivity extends AppCompatActivity {
//
//    private RecyclerView rvChatList;
//    private ChatListAdapter chatListAdapter;
//    private ArrayList<ChatListModel> chatListModels;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chat_list);
//
//        // Initialize views
//        rvChatList = findViewById(R.id.rv_chat_list);
//
//        // Set up RecyclerView
//        chatListModels = new ArrayList<>();
//        chatListAdapter = new ChatListAdapter(this, chatListModels);
//        rvChatList.setLayoutManager(new LinearLayoutManager(this));
//        rvChatList.setAdapter(chatListAdapter);
//
//        // Load chat list
//        loadChatList();
//    }
//
//    private void loadChatList() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        String currentUserId = auth.getCurrentUser().getUid();
//
//        db.collection("chat_lists")
//                .whereEqualTo("userIds." + currentUserId, true)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Log.e(TAG, "loadChatList: Failed to load chat list.", error);
//                            return;
//                        }
//
//                        if (value != null) {
//                            chatListModels.clear();
//                            for (QueryDocumentSnapshot document : value) {
//                                ChatListModel chatListModel = document.toObject(ChatListModel.class);
//                                chatListModel.setChatId(document.getId());
//                                chatListModels.add(chatListModel);
//                            }
//                            chatListAdapter.notifyDataSetChanged();
//                        }
//                    }
//
