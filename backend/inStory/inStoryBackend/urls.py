from django.urls import path
from . import views

urlpatterns = [
    path('users/', views.UserListView.as_view(), name='user-list'),
    path('users/<int:user_id>/friends/', views.UserFriendsListView.as_view(), name='user-friends-list'),
    path('users/<int:user_id>/albums/', views.UserAlbumsListView.as_view(), name='user-albums-list'),
    path('albums/<int:album_id>/telegrams/', views.AlbumTelegramsListView.as_view(), name='album-telegrams-list'),
    path('albums/<int:album_id>/participants/', views.AlbumParticipantsView.as_view(), name='album-participants-list'),
]