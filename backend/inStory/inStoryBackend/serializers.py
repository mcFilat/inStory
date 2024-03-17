from rest_framework import serializers
from .models import User, Album, Telegram, FriendsList, AlbumList


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'


class AlbumSerializer(serializers.ModelSerializer):
    class Meta:
        model = Album
        fields = '__all__'


class TelegramSerializer(serializers.ModelSerializer):
    class Meta:
        model = Telegram
        fields = '__all__'


class FriendsListSerializer(serializers.ModelSerializer):
    class Meta:
        model = FriendsList
        fields = '__all__'

class AlbumListSerializer(serializers.ModelSerializer):
    user = serializers.PrimaryKeyRelatedField(read_only=True)

    class Meta:
        model = AlbumList
        fields = '__all__'
