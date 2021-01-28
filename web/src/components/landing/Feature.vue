<template>
  <v-container>
    <v-row>
      <v-col class="md-8 mx-auto text-center">
        <span class="badge badge-primary badge-pill mt-16 mb-4">Feature</span>
        <h3 id="insight-title" class="my-10">Overview</h3>
        <p id="insight-title-contents">전자계약을 이용하는 사용자에게 계약서 템플릿 저장 및 관리, 양방향 이동 가능한 전자계약 프로세스를 제공합니다.</p>
      </v-col>
    </v-row>
    <v-row id="feature-contents" align="center">
      <v-col lg="4" offset="2">
        <div class="mb-12">
          <h4 class="my-4">계약서 통합 관리</h4>
          <p>분산된 계약서를 한 곳에서 통합 관리할 수 있습니다.</p>
          <router-link :to="'/templates'">템플릿관리 시작하기</router-link>
        </div>
        <div class="my-12">
          <h4 class="my-4">SaaS 모델</h4>
          <p>자사 인프라를 통해 최소의 사전 투자 비용으로 완전한 전자계약 솔루션을 제공합니다.</p>
          <router-link :to="'/contracts'">전자계약 시작하기</router-link>
        </div>
        <div class="mt-12">
          <h4 class="my-4">구축형 모델</h4>
          <p>고객사의 서버에 설치하여 완료된 계약서를 자체 보관 및 활용할 수 있습니다.</p>
          <router-link :to="'/history'">계약관리 시작하기</router-link>
        </div>
      </v-col>
      <v-col lg="4" class="mx-md-auto">
        <v-img :src="require('@/assets/ill.png')" width="100%"/>
      </v-col>
    </v-row>
  </v-container>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class Feature extends Vue {
}
</script>
<style lang="sass">
#feature-contents
  h4
    font-family: 'Nanum Square'
    color: #32325d
    font-size: 1.1rem
  p
    font-family: 'Nanum Gothic'
    color: #525f7f
    font-size: 0.9rem
  a
    font-family: 'Nanum Gothic'
    color: #11cdef
    font-size: 0.8rem
    text-decoration: none
    &:hover
      color: #233dd2
</style>
