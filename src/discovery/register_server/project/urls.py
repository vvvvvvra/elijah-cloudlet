from django.conf.urls import patterns, include, url
from cloudlets.api import CloudletResource
from tastypie.api import Api

v1_api = Api(api_name='v1')
v1_api.register(CloudletResource())

from django.contrib import admin
admin.autodiscover()

from cloudlets import views

urlpatterns = patterns('',
    url(r'^$', views.IndexView.as_view(), name='index'),
    url(r'^cloudlets/', include('cloudlets.urls')),

    # url(r'^project/', include('project.foo.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    url(r'^admin/', include(admin.site.urls)),
    (r'^api/', include(v1_api.urls)),
)
