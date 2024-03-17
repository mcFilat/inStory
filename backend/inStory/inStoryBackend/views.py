from rest_framework import status
from rest_framework.views import APIView
from rest_framework.response import Response
from django.shortcuts import get_object_or_404
from .models import User, Album, Telegram, FriendsList, AlbumList
from .serializers import UserSerializer, AlbumSerializer, TelegramSerializer, FriendsListSerializer, AlbumListSerializer


class UserListView(APIView):
    def get(self, request):
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserDetailView(APIView):
    def get(self, request, pk):
        user = get_object_or_404(User, pk=pk)
        serializer = UserSerializer(user)
        return Response(serializer.data)

    def put(self, request, pk):
        user = get_object_or_404(User, pk=pk)
        serializer = UserSerializer(user, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk):
        user = get_object_or_404(User, pk=pk)
        user.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class UserFriendsListView(APIView):
    def get(self, request, user_id):
        friends_lists = FriendsList.objects.filter(person_a=user_id)
        serializer = FriendsListSerializer(friends_lists, many=True)
        return Response(serializer.data)

    def post(self, request):
        serializer = FriendsListSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserAlbumsListView(APIView):
    def get(self, request, user_id):
        albums = AlbumList.objects.filter(user_id=user_id)
        serializer = AlbumListSerializer(albums, many=True)
        return Response(serializer.data)

    def post(self, request, user_id):
        data = request.data.copy()
        data['user_id'] = user_id
        serializer = AlbumListSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AlbumTelegramsListView(APIView):
    def get(self, request, album_id):
        telegrams = Telegram.objects.filter(albumid=album_id)
        serializer = TelegramSerializer(telegrams, many=True)
        return Response(serializer.data)

    def post(self, request, album_id):
        serializer = TelegramSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save(albumid=album_id)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class AlbumParticipantsView(APIView):
    def get(self, request, album_id):
        participants = AlbumList.objects.filter(album_id=album_id)
        serializer = AlbumListSerializer(participants, many=True)
        return Response(serializer.data)

    def post(self, request, album_id):
        data = request.data.copy()
        data['album_id'] = album_id
        serializer = AlbumListSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, album_id):
        # Получаем альбом
        album = Album.objects.get(pk=album_id)
        # Удаляем всех участников из AlbumList для данного альбома
        AlbumList.objects.filter(album=album).delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

class TelegramDetailView(APIView):
    def get(self, request, pk):
        telegram = get_object_or_404(Telegram, pk=pk)
        serializer = TelegramSerializer(telegram)
        return Response(serializer.data)

    def put(self, request, pk):
        telegram = get_object_or_404(Telegram, pk=pk)
        serializer = TelegramSerializer(telegram, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk):
        telegram = get_object_or_404(Telegram, pk=pk)
        telegram.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
