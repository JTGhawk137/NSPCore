package com.nonstoppvp.core.networking;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.nonstoppvp.core.NSPCore;
import com.nonstoppvp.core.config.FileManager;
import com.nonstoppvp.core.profiles.NSPPlayer;
import org.bson.Document;
import org.bukkit.Bukkit;

import static com.mongodb.client.model.Filters.eq;

public class MongoManager
{

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public void setupMongo()
    {
        try
        {
            MongoClientURI uri = new MongoClientURI(FileManager.get("mongo.yml").getString("URI"));
            mongoClient = new MongoClient(uri);
        } catch (Exception e)
        {
            System.out.println("Database couldn't connect.");
            Bukkit.getPluginManager().disablePlugin(NSPCore.getInstance());
        }
        database = mongoClient.getDatabase(FileManager.get("mongo.yml").getString("database"));
        collection = database.getCollection("Players");
        collection.createIndex(Indexes.ascending("uuid"));
        System.out.println("MongoDB has connected!");
    }

    public void createPlayerDocument(final NSPPlayer player, final Document document)
    {
        Bukkit.getScheduler().runTaskAsynchronously(NSPCore.getInstance(), () ->
        {
            if (!doesPlayerExist(player))
            {
                player.setDocument(document);
                collection.insertOne(document);
            }
        });
    }

    public void updatePlayerDocument(final NSPPlayer player, final Document document)
    {
        Bukkit.getScheduler().runTaskAsynchronously(NSPCore.getInstance(), () ->
        {
            if (doesPlayerExist(player))
                collection.updateOne(eq("uuid", player.getUuid().toString()), new Document("$set", document));
        });
    }

    public void savePlayerDocument(final NSPPlayer player, final Document document)
    {
        collection.updateOne(eq("uuid", player.getUuid().toString()), new Document("$set", document));
    }

    public boolean doesPlayerExist(NSPPlayer player)
    {
        return collection.find(new Document("uuid", player.getUuid().toString())).limit(1).iterator().hasNext();
    }

    public Document getPlayerDoc(final NSPPlayer player)
    {
        return collection.find(new Document("uuid", player.getUuid().toString())).first();
    }
}
