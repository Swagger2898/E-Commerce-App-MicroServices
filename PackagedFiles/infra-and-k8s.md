This file is a merged representation of a subset of the codebase, containing specifically included files and files not matching ignore patterns, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Only files matching these patterns are included: docker-compose.yml, k8s/**
- Files matching these patterns are excluded: services/**, **/target/**
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
```
docker-compose.yml
k8s/app-services.yaml
k8s/gh-kubeconfig.yaml
k8s/infra.yaml
k8s/monitoring.yaml
k8s/token.yaml
```

# Files

## File: k8s/gh-kubeconfig.yaml
```yaml
apiVersion: v1
kind: Config
clusters:
- name: oke-cluster
  cluster:
    certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURpVENDQW5HZ0F3SUJBZ0lSQUlzRHU3bVB3dkErc3JWMzRaRFZTOWd3RFFZSktvWklodmNOQVFFTEJRQXcKWGpFUE1BMEdBMVVFQXd3R1N6aHpJRU5CTVFzd0NRWURWUVFHRXdKVlV6RVBNQTBHQTFVRUJ3d0dRWFZ6ZEdsdQpNUTh3RFFZRFZRUUtEQVpQY21GamJHVXhEREFLQmdOVkJBc01BMDlqYVRFT01Bd0dBMVVFQ0F3RlZHVjRZWE13CkhoY05Nall3T1RBeU1USXdOalF3V2hjTk16RXdPVEF5TVRJd05qUXdXakJlTVE4d0RRWURWUVFEREFaTE9ITWcKUTBFeEN6QUpCZ05WQkFZVEFsVlRNUTh3RFFZRFZRUUhEQVpCZFhOMGFXNHhEekFOQmdOVkJBb01Cazl5WVdOcwpaVEVNTUFvR0ExVUVDd3dEVDJOcE1RNHdEQVlEVlFRSURBVlVaWGhoY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCCkJRQURnZ0VQQURDQ0FRb0NnZ0VCQU4xSExqZDMzRjQxTDkvYU02bFhiV3VZSzg2Y1RneWhnVExDaU9WZXpBdHIKdVJKd2o0Q2dzN1oyUDJuZ0xrTWRpdXpyeG1CdWRwaWJKQmZJTkcyWjRUbFFsQlVjZHU1b1RKaUZJNEY4N0tGbgoxeVErRDlNeXo0YlAxRnAzK3YxYWFjRGg5NTFjOUF0R29ZZnNOWVVtMVF1elVPMlhLaDhrNzVZNUVSY0NxUkRUCmtIYlA4bHFNOEJScjk5b3pKTHRvV2pEekhGcG1pS2ZEWmI0dFJyUnJmMXoxWHZLeUNpTkRSaDIwQW1yTmxKY1EKdzNKSFFsbHBrMDV6a2tWUDljekRHSHRQRjJ5NFljdkQzd0REMUFxZ3RzRElDZFhHK1daVGh4QVEyclQ0YnlMSwoyNk1GcC9tTWtYVExVRnhCNXF2cmt2ZzhoZTNFN3B2bTJjcTBqOU5Hb0RjQ0F3RUFBYU5DTUVBd0R3WURWUjBUCkFRSC9CQVV3QXdFQi96QU9CZ05WSFE4QkFmOEVCQU1DQVFZd0hRWURWUjBPQkJZRUZMZ0ZBWU1uRXoraFNMRkQKeVBsclNndnFCYlh1TUEwR0NTcUdTSWIzRFFFQkN3VUFBNElCQVFBVHdGcW85YnpOWGNjWVMwbGdVVW1XYmh3OAp4bFlxUzF4dk00YzBmOHVYNy9maEtXT2JwdFI1MFZNN25leXVYek43M0F0STZYcnRqQ2xJVWJ0OFZxeXB2Q05hCnRFVHJsdHROQUZpNVFieHFtdVZFY1RwUUVFT2ZRY0tHbmM1Q2UvektLVlROaWdVbjZQNG0vTWJMRnRNU2Y0NDUKTDdKelpaSWpOTTJDelRTdi9qYzNTMnZxZ2hKWHZObUFSWVJxWnI1djBNK29nOVVmOTRmRnd0V1ZMN3lpMWlxUApqRlVCcUtEMTR6YXZUVUluT3p2WnUxMVh4cGZQODVGa1RkUVhEb0VLbW1ub0xXcDVnZjZQRDZLU1NTK0kyM1BaCkNOVUpzN3c2K05GckJBVTF2TzdXbk1PR25HYkdFNVZiRGFLNWNRcTN0V1ZHVjRYZ3YyeGx2b2lHWXZIcwotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==
    server: https://140.245.5.202:6443
contexts:
- name: oke-context
  context:
    cluster: oke-cluster
    user: github-deployer
current-context: oke-context
users:
- name: github-deployer
  user:
    token: eyJhbGciOiJSUzI1NiIsImtpZCI6InpvRDl4VnZrZEJQVXh3bWpVd3hudmk0UVFKSF95a2dNQ0VSM1dqNXFVeDgifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJnaXRodWItZGVwbG95ZXItdG9rZW4iLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZ2l0aHViLWRlcGxveWVyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNDBiYTk4MGUtODE0ZC00YWJkLWExZWYtZDM0MGFkYWE0NWM5Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmdpdGh1Yi1kZXBsb3llciJ9.lp7E7DoaHlp7f3vnlpGuuuWG2C4C6eRmSVJRUpTNpxJidaQ2G2YnuVsM5NDxnASwCwKD4f-KOvAnHpAkR6cg64UlUiLs16V5vOmTicl62Aa6q3vgVw9SZV2WIp78S7v-VexHHnYWbJFs0eccbTO4ScpL921Ddd5za88Oib9lBwCdPQMG8eIYEUqwq8KBi6mXncpCD9A8AoZ7mx6TEbxom-3BEZZBdxKTlcUnu_pIHceNZ6V4CuiLue22_1A6W6vstyE4uSdfoI0XSxDVQy81Vz_3YpurngG-teP8kVBNnebXudA_gWypLBID-WmNAqjJXMqyNtbofbyjlhrTbWdRzA
```

## File: k8s/monitoring.yaml
```yaml
# Prometheus ConfigMap
# Scrapes pods using Prometheus annotations and automatically discovers new pods.

apiVersion: v1
kind: ConfigMap
metadata:
  name: prometheus-config
  namespace: ecommerce
data:
  prometheus.yml: |
    global:
      scrape_interval: 15s
    
    scrape_configs:
      - job_name: 'k8s-pods'
        kubernetes_sd_configs:
          - role: pod
            namespaces:
              names: ['ecommerce']
    
        relabel_configs:
          - source_labels: [__meta_kubernetes_pod_annotation_prometheus_io_scrape]
            action: keep
            regex: true
    
          - source_labels: [__meta_kubernetes_pod_annotation_prometheus_io_path]
            action: replace
            target_label: __metrics_path__
            regex: (.+)
    
          - source_labels: [__address__, __meta_kubernetes_pod_annotation_prometheus_io_port]
            action: replace
            regex: ([^:]+)(?::\d+)?;(\d+)
            replacement: $1:$2
            target_label: __address__
    
          - source_labels: [__meta_kubernetes_pod_label_app]
            target_label: app

---
# Prometheus needs RBAC permissions to discover pods.

apiVersion: v1
kind: ServiceAccount
metadata:
  name: prometheus
  namespace: ecommerce

---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: prometheus
rules:
  - apiGroups: [""]
    resources: ["pods"]
    verbs: ["get", "list", "watch"]

---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: prometheus
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: prometheus
subjects:
  - kind: ServiceAccount
    name: prometheus
    namespace: ecommerce

---
# Prometheus Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: prometheus
  namespace: ecommerce
spec:
  replicas: 1
  selector:
    matchLabels:
      app: prometheus

  template:
    metadata:
      labels:
        app: prometheus

    spec:
      serviceAccountName: prometheus

      containers:
        - name: prometheus
          image: docker.io/prom/prometheus:latest
          ports:
            - containerPort: 9090

          volumeMounts:
            - name: config
              mountPath: /etc/prometheus

      volumes:
        - name: config
          configMap:
            name: prometheus-config

---
# Prometheus Service

apiVersion: v1
kind: Service
metadata:
  name: prometheus
  namespace: ecommerce
spec:
  selector:
    app: prometheus

  ports:
    - port: 9090
      targetPort: 9090

---
# Grafana datasource configuration
# Prometheus is automatically configured as the default datasource.

apiVersion: v1
kind: ConfigMap
metadata:
  name: grafana-datasources
  namespace: ecommerce
data:
  datasource.yml: |
    apiVersion: 1
    
    datasources:
      - name: Prometheus
        type: prometheus
        access: proxy
        url: http://prometheus:9090
        isDefault: true

---
# Grafana Deployment

apiVersion: apps/v1
kind: Deployment
metadata:
  name: grafana
  namespace: ecommerce
spec:
  replicas: 1
  selector:
    matchLabels:
      app: grafana

  template:
    metadata:
      labels:
        app: grafana

    spec:
      containers:
        - name: grafana
          image: docker.io/grafana/grafana:latest
          ports:
            - containerPort: 3000

          volumeMounts:
            - name: datasources
              mountPath: /etc/grafana/provisioning/datasources

      volumes:
        - name: datasources
          configMap:
            name: grafana-datasources

---
# Grafana Service

apiVersion: v1
kind: Service
metadata:
  name: grafana
  namespace: ecommerce
spec:
  selector:
    app: grafana

  ports:
    - port: 3000
      targetPort: 3000
```

## File: k8s/token.yaml
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: github-deployer-token
  namespace: kube-system
  annotations:
    kubernetes.io/service-account.name: github-deployer
type: kubernetes.io/service-account-token
```

## File: k8s/infra.yaml
```yaml
apiVersion: v1
kind: Namespace
metadata:
  name: ecommerce
---
#apiVersion: v1
#kind: Secret
#metadata:
#  name: infra-credentials
#  namespace: ecommerce
#type: Opaque
#stringData:
#  POSTGRES_USER: username
#  POSTGRES_PASSWORD: password
#  MONGO_INITDB_ROOT_USERNAME: username
#  MONGO_INITDB_ROOT_PASSWORD: password
#  PGADMIN_DEFAULT_EMAIL: pgadmin4@pgadmin.org
#  PGADMIN_DEFAULT_PASSWORD: admin
#  KEYCLOAK_ADMIN: admin
#  KEYCLOAK_ADMIN_PASSWORD: admin
#---
# ---------------- PostgreSQL ----------------
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: postgresql
  namespace: ecommerce
spec:
  serviceName: postgresql
  replicas: 1
  selector:
    matchLabels: { app: postgresql }
  template:
    metadata:
      labels: { app: postgresql }
    spec:
      containers:
        - name: postgresql
          image: docker.io/library/postgres:latest
          ports: [{ containerPort: 5432 }]
          env:
            - { name: POSTGRES_USER, valueFrom: { secretKeyRef: { name: infra-credentials, key: POSTGRES_USER } } }
            - { name: POSTGRES_PASSWORD, valueFrom: { secretKeyRef: { name: infra-credentials, key: POSTGRES_PASSWORD } } }
            - { name: PGDATA, value: /var/lib/postgresql/data/pgdata }
          volumeMounts:
            - { name: postgres-data, mountPath: /var/lib/postgresql/data }
  volumeClaimTemplates:
    - metadata: { name: postgres-data }
      spec:
        accessModes: ["ReadWriteOnce"]
        resources: { requests: { storage: 5Gi } }
---
apiVersion: v1
kind: Service
metadata:
  name: postgresql
  namespace: ecommerce
spec:
  selector: { app: postgresql }
  ports: [{ port: 5432, targetPort: 5432 }]
---
# ---------------- pgAdmin ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: pgadmin
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: pgadmin } }
  template:
    metadata: { labels: { app: pgadmin } }
    spec:
      containers:
        - name: pgadmin
          image: docker.io/dpage/pgadmin4:latest
          ports: [{ containerPort: 80 }]
          env:
            - { name: PGADMIN_DEFAULT_EMAIL, valueFrom: { secretKeyRef: { name: infra-credentials, key: PGADMIN_DEFAULT_EMAIL } } }
            - { name: PGADMIN_DEFAULT_PASSWORD, valueFrom: { secretKeyRef: { name: infra-credentials, key: PGADMIN_DEFAULT_PASSWORD } } }
          volumeMounts:
            - { name: pgadmin-data, mountPath: /var/lib/pgadmin }
      volumes:
        - name: pgadmin-data
          persistentVolumeClaim: { claimName: pgadmin-pvc }
---
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: pgadmin-pvc
  namespace: ecommerce
spec:
  accessModes: ["ReadWriteOnce"]
  resources: { requests: { storage: 1Gi } }
---
apiVersion: v1
kind: Service
metadata:
  name: pgadmin
  namespace: ecommerce
spec:
  selector: { app: pgadmin }
  ports: [{ port: 5050, targetPort: 80 }]
  type: ClusterIP
---
# ---------------- MongoDB ----------------
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mongodb
  namespace: ecommerce
spec:
  serviceName: mongodb
  replicas: 1
  selector: { matchLabels: { app: mongodb } }
  template:
    metadata: { labels: { app: mongodb } }
    spec:
      containers:
        - name: mongodb
          image: docker.io/library/mongo:latest
          ports: [{ containerPort: 27017 }]
          env:
            - { name: MONGO_INITDB_ROOT_USERNAME, valueFrom: { secretKeyRef: { name: infra-credentials, key: MONGO_INITDB_ROOT_USERNAME } } }
            - { name: MONGO_INITDB_ROOT_PASSWORD, valueFrom: { secretKeyRef: { name: infra-credentials, key: MONGO_INITDB_ROOT_PASSWORD } } }
          volumeMounts:
            - { name: mongo-data, mountPath: /data/db }
  volumeClaimTemplates:
    - metadata: { name: mongo-data }
      spec:
        accessModes: ["ReadWriteOnce"]
        resources: { requests: { storage: 5Gi } }
---
apiVersion: v1
kind: Service
metadata:
  name: mongodb
  namespace: ecommerce
spec:
  selector: { app: mongodb }
  ports: [{ port: 27017, targetPort: 27017 }]
---
# ---------------- Mongo Express ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongo-express
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: mongo-express } }
  template:
    metadata: { labels: { app: mongo-express } }
    spec:
      containers:
        - name: mongo-express
          image: docker.io/library/mongo-express:latest
          ports: [{ containerPort: 8081 }]
          env:
            - { name: ME_CONFIG_MONGODB_ADMINUSERNAME, valueFrom: { secretKeyRef: { name: infra-credentials, key: MONGO_INITDB_ROOT_USERNAME } } }
            - { name: ME_CONFIG_MONGODB_ADMINPASSWORD, valueFrom: { secretKeyRef: { name: infra-credentials, key: MONGO_INITDB_ROOT_PASSWORD } } }
            - { name: ME_CONFIG_MONGODB_URL, value: "mongodb://$(ME_CONFIG_MONGODB_ADMINUSERNAME):$(ME_CONFIG_MONGODB_ADMINPASSWORD)@mongodb:27017/" }
            - { name: ME_CONFIG_BASICAUTH, value: "false" }
---
apiVersion: v1
kind: Service
metadata:
  name: mongo-express
  namespace: ecommerce
spec:
  selector: { app: mongo-express }
  ports: [{ port: 8081, targetPort: 8081 }]
---
# ---------------- Zookeeper ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: zookeeper
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: zookeeper } }
  template:
    metadata: { labels: { app: zookeeper } }
    spec:
      containers:
        - name: zookeeper
          image: docker.io/confluentinc/cp-zookeeper:latest
          ports: [{ containerPort: 2181 }]
          env:
            - { name: ZOOKEEPER_SERVER_ID, value: "1" }
            - { name: ZOOKEEPER_CLIENT_PORT, value: "2181" }
            - { name: ZOOKEEPER_TICK_TIME, value: "2000" }
---
apiVersion: v1
kind: Service
metadata:
  name: zookeeper
  namespace: ecommerce
spec:
  selector: { app: zookeeper }
  ports: [{ port: 2181, targetPort: 2181 }]
---
# ---------------- Kafka ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: kafka
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: kafka } }
  template:
    metadata: { labels: { app: kafka } }
    spec:
      enableServiceLinks: false
      containers:
        - name: kafka
          image: docker.io/confluentinc/cp-kafka:7.4.3
          ports: [{ containerPort: 9092 }]
          env:
            - { name: KAFKA_ZOOKEEPER_CONNECT, value: "zookeeper:2181" }
            - { name: KAFKA_LISTENERS, value: "PLAINTEXT://0.0.0.0:9092" }
            - { name: KAFKA_ADVERTISED_LISTENERS, value: "PLAINTEXT://kafka:9092" }
            - { name: KAFKA_LISTENER_SECURITY_PROTOCOL_MAP, value: "PLAINTEXT:PLAINTEXT" }
            - { name: KAFKA_INTER_BROKER_LISTENER_NAME, value: "PLAINTEXT" }
            - { name: KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR, value: "1" }
            - { name: KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR, value: "1" }
            - { name: KAFKA_TRANSACTION_STATE_LOG_MIN_ISR, value: "1" }
---
apiVersion: v1
kind: Service
metadata:
  name: kafka
  namespace: ecommerce
spec:
  selector: { app: kafka }
  ports: [{ port: 9092, targetPort: 9092 }]
---
# ---------------- MailDev ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mail-dev
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: mail-dev } }
  template:
    metadata: { labels: { app: mail-dev } }
    spec:
      containers:
        - name: mail-dev
          image: docker.io/maildev/maildev:latest
          ports:
            - { containerPort: 1080 }
            - { containerPort: 1025 }
---
apiVersion: v1
kind: Service
metadata:
  name: mail-dev
  namespace: ecommerce
spec:
  selector: { app: mail-dev }
  ports:
    - { name: web, port: 1080, targetPort: 1080 }
    - { name: smtp, port: 1025, targetPort: 1025 }
---
# ---------------- Zipkin ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: zipkin
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: zipkin } }
  template:
    metadata: { labels: { app: zipkin } }
    spec:
      containers:
        - name: zipkin
          image: docker.io/openzipkin/zipkin:latest
          ports: [{ containerPort: 9411 }]
---
apiVersion: v1
kind: Service
metadata:
  name: zipkin
  namespace: ecommerce
spec:
  selector: { app: zipkin }
  ports: [{ port: 9411, targetPort: 9411 }]
---
# ---------------- Keycloak ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: keycloak
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: keycloak } }
  template:
    metadata: { labels: { app: keycloak } }
    spec:
      containers:
        - name: keycloak
          image: quay.io/keycloak/keycloak:26.0.0
          args: ["start", "--hostname-strict=false", "--http-enabled=true"]
          ports: [{ containerPort: 8080 }]
          env:
            - { name: KEYCLOAK_ADMIN, valueFrom: { secretKeyRef: { name: infra-credentials, key: KEYCLOAK_ADMIN } } }
            - { name: KEYCLOAK_ADMIN_PASSWORD, valueFrom: { secretKeyRef: { name: infra-credentials, key: KEYCLOAK_ADMIN_PASSWORD } } }
            - { name: KC_DB, value: "postgres" }
            - { name: KC_DB_URL, value: "jdbc:postgresql://postgresql:5432/keycloak" }
            - { name: KC_DB_USERNAME, valueFrom: { secretKeyRef: { name: infra-credentials, key: POSTGRES_USER } } }
            - { name: KC_DB_PASSWORD, valueFrom: { secretKeyRef: { name: infra-credentials, key: POSTGRES_PASSWORD } } }
```

## File: k8s/app-services.yaml
```yaml
# ---------------- config-server (no deps) ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: config-server
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: config-server } }
  template:
    metadata:
      labels: { app: config-server }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8888"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      containers:
        - name: config-server
          image: ghcr.io/swagger2898/config-server:latest
          imagePullPolicy: Always
          command: ["java", "-jar", "app.jar", "--spring.profiles.active=native"]
          ports: [{ containerPort: 8888 }]
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8888
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8888
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: config-server
  namespace: ecommerce
spec:
  selector: { app: config-server }
  ports: [{ port: 8888, targetPort: 8888 }]
---
# ---------------- discovery (no deps) ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: discovery
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: discovery } }
  template:
    metadata:
      labels: { app: discovery }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8761"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
      containers:
        - name: discovery
          image: ghcr.io/swagger2898/discovery:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8761 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8761
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8761
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: discovery
  namespace: ecommerce
spec:
  selector: { app: discovery }
  ports: [{ port: 8761, targetPort: 8761 }]
---
# ---------------- customer ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: customer
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: customer } }
  template:
    metadata:
      labels: { app: customer }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8090"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
        - name: wait-for-discovery
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z discovery 8761; do echo waiting for discovery; sleep 3; done']
      containers:
        - name: customer
          image: ghcr.io/swagger2898/customer:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8090 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8090
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8090
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: customer
  namespace: ecommerce
spec:
  selector: { app: customer }
  ports: [{ port: 8090, targetPort: 8090 }]
---
# ---------------- product ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: product } }
  template:
    metadata:
      labels: { app: product }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8050"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
        - name: wait-for-discovery
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z discovery 8761; do echo waiting for discovery; sleep 3; done']
      containers:
        - name: product
          image: ghcr.io/swagger2898/product:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8050 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8050
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8050
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: product
  namespace: ecommerce
spec:
  selector: { app: product }
  ports: [{ port: 8050, targetPort: 8050 }]
---
# ---------------- payment ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: payment
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: payment } }
  template:
    metadata:
      labels: { app: payment }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8060"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
        - name: wait-for-discovery
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z discovery 8761; do echo waiting for discovery; sleep 3; done']
      containers:
        - name: payment
          image: ghcr.io/swagger2898/payment:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8060 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8060
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8060
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: payment
  namespace: ecommerce
spec:
  selector: { app: payment }
  ports: [{ port: 8060, targetPort: 8060 }]
---
# ---------------- order ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: order
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: order } }
  template:
    metadata:
      labels: { app: order }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8070"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
        - name: wait-for-discovery
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z discovery 8761; do echo waiting for discovery; sleep 3; done']
      containers:
        - name: order
          image: ghcr.io/swagger2898/order:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8070 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8070
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8070
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: order
  namespace: ecommerce
spec:
  selector: { app: order }
  ports: [{ port: 8070, targetPort: 8070 }]
---
# ---------------- notification ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: notification
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: notification } }
  template:
    metadata:
      labels: { app: notification }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8040"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
        - name: wait-for-discovery
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z discovery 8761; do echo waiting for discovery; sleep 3; done']
      containers:
        - name: notification
          image: ghcr.io/swagger2898/notification:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8040 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8040
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8040
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: notification
  namespace: ecommerce
spec:
  selector: { app: notification }
  ports: [{ port: 8040, targetPort: 8040 }]
---
# ---------------- gateway (waits on everything, exposed externally) ----------------
apiVersion: apps/v1
kind: Deployment
metadata:
  name: gateway
  namespace: ecommerce
spec:
  replicas: 1
  selector: { matchLabels: { app: gateway } }
  template:
    metadata:
      labels: { app: gateway }
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8222"
        prometheus.io/path: "/actuator/prometheus"
    spec:
      initContainers:
        - name: wait-for-config-server
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z config-server 8888; do echo waiting for config-server; sleep 3; done']
        - name: wait-for-discovery
          image: busybox:1.36
          command: ['sh', '-c', 'until nc -z discovery 8761; do echo waiting for discovery; sleep 3; done']
      containers:
        - name: gateway
          image: ghcr.io/swagger2898/gateway:latest
          imagePullPolicy: Always
          ports: [{ containerPort: 8222 }]
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: SPRING_CONFIG_IMPORT
              value: "optional:configserver:http://config-server:8888"
          resources:
            requests:
              cpu: 200m
              memory: 384Mi
            limits:
              cpu: 500m
              memory: 512Mi
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8222
            initialDelaySeconds: 20
            periodSeconds: 10
            failureThreshold: 3
          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8222
            initialDelaySeconds: 30
            periodSeconds: 15
            failureThreshold: 3
---
apiVersion: v1
kind: Service
metadata:
  name: gateway
  namespace: ecommerce
spec:
  selector: { app: gateway }
  ports: [{ port: 8222, targetPort: 8222 }]
  type: LoadBalancer
```

## File: docker-compose.yml
```yaml
services:
  postgresql:
    container_name: ms_pg_sql
    image: postgres
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
      PGDATA: /var/lib/data/postgres

    volumes:
      - postgres:/var/lib/data/postgres
    ports:
      - 5432:5432
    networks:
      - microservices-net
    restart: unless-stopped

  pgadmin:
    container_name: ms_pgadmin
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: pgadmin4@pgadmin.org
      PGADMIN_DEFAULT_PASSWORD: admin

    volumes:
      - pgadmin:/var/lib/pgadmin
    ports:
      - "5050:80"
    networks:
      - microservices-net
    restart: unless-stopped




  mongodb:
    image: mongo
    container_name: mongo_db
    ports:
      - 27017:27017
    volumes:
      - mongo:/data/db

    environment:
      - MONGO_INITDB_ROOT_USERNAME=username
      - MONGO_INITDB_ROOT_PASSWORD=password
    networks:
      - microservices-net



  mongo-express:
    image: mongo-express
    container_name: mongo_express
    ports:
      - 8081:8081
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: username
      ME_CONFIG_MONGODB_ADMINPASSWORD: password
      ME_CONFIG_MONGODB_URL: mongodb://username:password@mongodb:27017/
      ME_CONFIG_BASICAUTH: "false"
    depends_on:
      - mongodb
    networks:
      - microservices-net
    restart: unless-stopped



  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    container_name: zookeeper
    environment:
      ZOOKEEPER_SERVER_ID: 1
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - 22181:2181
    networks:
      - microservices-net

  kafka:
    image: confluentinc/cp-kafka:7.4.3
    container_name: ms_kafka
    ports:
      - 29092:29092
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      # Two listeners: one for internal (containers) and one for host
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:29092
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
    depends_on:
      - zookeeper
    networks:
      - microservices-net






  mail-dev:
    container_name: ms-mail-dev
    image: maildev/maildev
    ports:
      - 1080:1080
      - 1025:1025
    networks:
      - microservices-net
  zipkin:
    container_name: zipkin
    image: openzipkin/zipkin
    ports:
     - 9411:9411   #  machine:docker
    networks:
      - microservices-net

  keycloak:
    image: quay.io/keycloak/keycloak:26.0.0
    container_name: ms_keycloak
    command: start-dev
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
    ports:
      - "8080:8080"
    networks:
      - microservices-net


networks:
  microservices-net:
    driver: bridge



volumes:
  postgres:
  pgadmin:
  mongo:
```
